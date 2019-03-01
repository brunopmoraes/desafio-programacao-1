package com.nexaas.service;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.nexaas.domain.File;
import com.nexaas.exception.FileStorageException;
import com.nexaas.property.FileStorageProperties;
import com.nexaas.repository.FileRepository;
import com.nexaas.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    private FileRepository repository;

    final Locale brLocale = new Locale("pt", "BR");

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Transactional
    public String storeFile(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        BufferedReader buf = null;
        try {

            InputStream inputStream = file.getInputStream();
            buf = new BufferedReader(new InputStreamReader(inputStream));

            Stream<String> lines = buf.lines();
            List<String> list = Lists.newArrayList(lines.iterator());

            List<File> files = new ArrayList<>();
            for (String line : Iterables.skip(list, 1) ) {
                files.add(Converter.stringToDTO(line));
            }

            BigDecimal sum = files.stream().map(File::getItemPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
            repository.saveAll(files);


            final NumberFormat brFormat = NumberFormat.getCurrencyInstance(brLocale);

            return brFormat.format(sum);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }finally {
            try {
                buf.close();
            } catch (IOException ex) {
                throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
            }
        }
    }
}