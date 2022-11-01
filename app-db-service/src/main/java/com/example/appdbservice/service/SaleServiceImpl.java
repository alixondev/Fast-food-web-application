package com.example.appdbservice.service;

import com.example.appdbservice.entity.order.Sale;
import com.example.appdbservice.exception.RestException;
import com.example.appdbservice.mapper.SaleMapper;
import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.SaleAddDTO;
import com.example.appdbservice.payload.SaleInfoDTO;
import com.example.appdbservice.repository.order.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;

    private final SaleMapper saleMapper;

    @Override
    public ApiResult<List<SaleInfoDTO>> getAll(int page, int size) {
        List<Sale> saleList = saleRepository.findAll();
        List<SaleInfoDTO> saleInfoDTOList = new ArrayList<>();
        for (Sale sale : saleList) {
            saleInfoDTOList.add(saleMapper.saleResToDoDTO(sale));
        }
        return ApiResult.successResponse(saleInfoDTOList);

    }

    @Override
    public ApiResult<SaleInfoDTO> getOne(Long id) {
        Sale sale = getByIdOrElseThrow(id);
        if (Objects.isNull(sale)) {
            throw RestException.notFound("Sale");
        }
        return ApiResult.successResponse(entityToInfoDTO(sale));
    }

    @Override
    public ApiResult<SaleInfoDTO> add(SaleAddDTO saleAddDTO) {
        checkExistingSales(saleAddDTO.getStartTime(),
                saleAddDTO.getEndTime(),
                saleAddDTO.getStartDate(),
                saleAddDTO.getEndDate(),
                null);

        Sale sale = new Sale(
                saleAddDTO.getName(),
                saleAddDTO.getDescription(),
                saleAddDTO.getType(),
                saleAddDTO.getAmount(),
                saleAddDTO.getStartTime(),
                saleAddDTO.getEndTime(),
                saleAddDTO.getStartDate(),
                saleAddDTO.getEndDate()
        );
        Sale save = saleRepository.save(sale);
        return ApiResult.successResponse(saleMapper.saleResToDoDTO(save));
    }

    @Override
    public ApiResult<SaleInfoDTO> update(SaleAddDTO saleAddDTO, Long id) {
        Sale sale = saleRepository.findById(id).orElseThrow(() -> new RestException("Sale", HttpStatus.NOT_FOUND));
        Date currentDate = new Date(System.currentTimeMillis());
        Date startDate = saleAddDTO.getStartDate();
        if (currentDate.after(startDate)) {
            checkExistingSales(saleAddDTO.getStartTime(),
                    saleAddDTO.getEndTime(),
                    saleAddDTO.getStartDate(),
                    saleAddDTO.getEndDate(),
                    id);
            sale.setEndDate(saleAddDTO.getEndDate());
            sale.setEndTime(saleAddDTO.getEndTime());
            sale.setStartTime(saleAddDTO.getStartTime());
            sale.setDescription(saleAddDTO.getDescription());
            sale.setSaleType(saleAddDTO.getType());
            sale.setName(saleAddDTO.getName());
            sale.setAmount(saleAddDTO.getAmount());
            Sale save = saleRepository.save(sale);
            return ApiResult.successResponse(entityToInfoDTO(save));
        } else {
            checkExistingSales(saleAddDTO.getStartTime(),
                    saleAddDTO.getEndTime(),
                    saleAddDTO.getStartDate(),
                    saleAddDTO.getEndDate(),
                    id);
            sale.setStartDate(saleAddDTO.getStartDate());
            sale.setEndDate(saleAddDTO.getEndDate());
            sale.setEndTime(saleAddDTO.getEndTime());
            sale.setStartTime(saleAddDTO.getStartTime());
            sale.setDescription(saleAddDTO.getDescription());
            sale.setSaleType(saleAddDTO.getType());
            sale.setName(saleAddDTO.getName());
            sale.setAmount(saleAddDTO.getAmount());
            Sale save = saleRepository.save(sale);
            SaleInfoDTO saleInfoDTO = entityToInfoDTO(save);
            return ApiResult.successResponse(saleInfoDTO);
        }
    }

    @Override
    public ApiResult<?> delete(Long id) {
        Sale sale = getByIdOrElseThrow(id);
        saleRepository.delete(sale);
        return ApiResult.successResponse("Success");
    }

    private SaleInfoDTO entityToInfoDTO(Sale sale) {
        return saleMapper.entityToInfoDTO(sale);
    }

    public SaleAddDTO entityToAddDTO(Sale sale) {
        return new SaleAddDTO(
                sale.getName(),
                sale.getDescription(),
                sale.getSaleType(),
                sale.getAmount(),
                sale.getStartTime(),
                sale.getEndTime(),
                sale.getStartDate(),
                sale.getEndDate()
        );
    }

    public Sale getByIdOrElseThrow(Long id) {
        return saleRepository.findById(id).orElseThrow(()
                -> RestException.notFound("Sale"));
    }

    private void checkExistingSales(java.sql.Timestamp startTime, Timestamp endTime, Date startDate,
                                    Date endDate, Long id) {


        Date date = new Date(System.currentTimeMillis());
        Date datePlusOneDay = new Date(System.currentTimeMillis() + 86000000);

        List<Sale> existsByGivenTimeAndDate;

        if (id != null)
            existsByGivenTimeAndDate = saleRepository.getExistsByGivenTimeIdNot(
                    startDate,
                    endDate,
                    startTime,
                    endTime,
                    date,
                    datePlusOneDay,
                    id
            );
        else
            existsByGivenTimeAndDate = saleRepository.getExistsByGivenTime(
                    startDate,
                    endDate,
                    startTime,
                    endTime,
                    date,
                    datePlusOneDay
            );

        if (existsByGivenTimeAndDate.isEmpty())
            throw new RestException("There is conflict with Time!",HttpStatus.CONFLICT);

    }
}

