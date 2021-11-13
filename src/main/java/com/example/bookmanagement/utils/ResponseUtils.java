package com.example.bookmanagement.utils;

import com.example.bookmanagement.dto.MetadataDTO;
import com.example.bookmanagement.dto.ResponseDTO;
import com.example.bookmanagement.exception.BookBusinessException;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {
    public static final String CODE_OK = "200";
    public static final String MESSAGE_OK = "Success";

    public static final String CODE_BAD_REQUEST = "400";

    public static ResponseDTO buildResponse(String code, String message, Object data){
        MetadataDTO meta = new MetadataDTO(code, message);
        return new ResponseDTO(meta, data);
    }

    public static ResponseDTO responseOK(Object data){
        return buildResponse(CODE_OK, MESSAGE_OK, data);
    }

    public static ResponseDTO responseBadRequest(String message){
        return buildResponse(CODE_BAD_REQUEST, message, null);
    }

    public static ResponseDTO buildError(MetadataDTO metaData) {
        return new ResponseDTO(metaData, null);
    }

    public static ResponseDTO buildError(String code, String message) {
        return new ResponseDTO(new MetadataDTO(code, message), null);
    }

    public static ResponseEntity<ResponseDTO> response(ResponseDTO responseDTO) {
        return ResponseEntity.ok().body(responseDTO);
    }

    public static ResponseDTO buildError(BookBusinessException e) {
        return new ResponseDTO(new MetadataDTO(e.getCode(), e.getMessage()), null);
    }
}
