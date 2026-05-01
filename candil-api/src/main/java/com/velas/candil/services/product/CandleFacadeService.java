package com.velas.candil.services.product;

import com.velas.candil.entities.user.User;
import com.velas.candil.models.candle.CandleRequestDto;
import com.velas.candil.models.candle.CandleResponseDto;
import com.velas.candil.services.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CandleFacadeService {

    private final CandleService candleService;
    private final FileService fileService;

    public CandleResponseDto create(
            CandleRequestDto data,
            MultipartFile principalImage,
            List<MultipartFile> images,
            User user
    ) throws IOException {

        if (principalImage == null || principalImage.isEmpty()) {
            throw new IllegalArgumentException("Principal image is required");
        }

        String baseKey = UUID.randomUUID().toString();

        String principalKey = fileService.uploadSingleFile(principalImage, baseKey);

        List<String> imageKeys = (images != null && !images.isEmpty())
                ? fileService.uploadMultipleFiles(images, baseKey)
                : List.of();

        CandleRequestDto finalDto = new CandleRequestDto(
                data.name(),
                data.description(),
                principalKey,
                data.stock(),
                data.materialEnums(),
                data.featureEnums(),
                data.categories(),
                imageKeys,
                data.ingredients()
        );

        log.info("Creating candle '{}' by user '{}'", data.name(), user.getUsername());
        return candleService.create(finalDto);
    }
}
