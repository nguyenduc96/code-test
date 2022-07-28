package com.sotatek.codingtest.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONPOJOBuilder;
import com.alibaba.fastjson2.util.IOUtils;
import com.fasterxml.jackson.annotation.JsonValue;
import com.sotatek.codingtest.core.config.BuildHtml;
import com.sotatek.codingtest.domain.dto.PostDetailDTO;
import com.sotatek.codingtest.domain.dto.RevpayResponse;
import com.sotatek.codingtest.domain.entity.PostDetail;
import com.sotatek.codingtest.helper.ResultResp;
import com.sotatek.codingtest.repository.PostDetailRepository;
import com.sotatek.codingtest.service.postdetail.PostDetailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping("api")
@Log4j2
public class PostDetailController {
    @Autowired
    private PostDetailService postDetailService;

    @Autowired
    private PostDetailRepository postDetailRepository;

    @PostMapping("/datafeed")
    public String data(String req) {
        log.info(req);
        return "OK";
    }

    @GetMapping("/successUrl")
    public String dataSuccess(@RequestParam String req) {
        log.info(req);
        return "OK";
    }


    @GetMapping("/revpay")
    public ResponseEntity<?> geta() {
        return new ResponseEntity<>(JSONObject.toJSON(null), HttpStatus.OK);
    }

    @PostMapping("/revpay")
    public String postR(@RequestBody String revpayResponse) {
        System.out.println(revpayResponse);
        RevpayResponse response = JSONObject.parseObject(revpayResponse, RevpayResponse.class);

        return response.toString();
    }

    @PostMapping("/web/{id}")
    public String www(@PathVariable Long id, Model model) {
//        List<PostDetail> postDetails = postDetailRepository.findByPostcodesInOrderBySuburbNames(strings);
//        Map<String, String> map = new HashMap<>();
//        postDetails.stream().forEach(postDetail -> {
//            map.put(postDetail.getPostcodes(), postDetail.getSuburbNames());
//        });


        PostDetailDTO postDetailDTO = new PostDetailDTO(postDetailRepository.getOne(id));
        model.addAttribute("suburbNames", postDetailDTO.getSuburbNames());
        model.addAttribute("postcodes", postDetailDTO.getPostcodes());

        return BuildHtml.build("Title", "localhost:8080/api/post/create/a", model.asMap());
    }

    @PostMapping(value = "/test", consumes = {MediaType.ALL_VALUE},produces = {MediaType.ALL_VALUE})
    public String byteB(InputStream is) throws IOException {
//        String s = "";
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        StringBuilder out = new StringBuilder();
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        Reader in = new InputStreamReader(is, StandardCharsets.UTF_8);
        for (int length; (length = is.read(buffer)) != -1; ) {
            result.write(buffer, 0, length);
        }
//        String rs = "";
//        for (int i = 0; i < 1000; i++) {
//            rs += result.toByteArray()[i];
//        }
//        log.info(rs);
        setData(result.toByteArray());
//        log.info(Arrays.toString(result.toByteArray()));
//        log.info(multipartFile.getContentType());
//        log.info(map);
//        log.info(is.toString());
        return "OK";
    }

    public void setData(byte[] data) throws IOException {
        ByteBuffer byteBuffer;
        byteBuffer = ByteBuffer.wrap(data);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        String s = "";
        for (int i = 0; i < 310; i++) {
            s += (char) byteBuffer.get();
        }
        log.info(s);
//        for (int i = 0; i < x; i++) {
//            fileName +=  (char) byteBuffer.get();
//        }
//        byte[] newBytes = new byte[data.length - 19];
//log.info(fileName);
//        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
////        int len = 19;
//        System.arraycopy(data, 18, newBytes, 0, newBytes.length);
//        fileOutputStream.write(newBytes);
//        fileOutputStream.close();
        log.info("Ok");
    }


    @GetMapping("post")
    public ResultResp<?> getAll(@RequestBody(required = false) List<String> postcodes) {
        ResultResp<?> resultResp;
        try {
            if (postcodes != null) {
                resultResp = new ResultResp<>(HttpStatus.OK, postDetailService.findAllByPostcode(postcodes));
            } else {
                resultResp= new ResultResp<>(HttpStatus.OK, postDetailService.findAll());
            }
        } catch (Exception e) {
            resultResp = new ResultResp<>(HttpStatus.BAD_REQUEST);
        }
        return resultResp;
    }

    @GetMapping("post/create")
    public ResultResp<?> create(@RequestBody PostDetailDTO postDetailDTO) {
        ResultResp<?> resultResp;
        try {
            if (postDetailDTO.getSuburbNames() == null || postDetailDTO.getPostcodes() == null) {
                resultResp= new ResultResp<>(HttpStatus.BAD_REQUEST);
            } else {
                resultResp = new ResultResp<>(HttpStatus.OK, postDetailService.save(postDetailDTO));
            }
        } catch (Exception e) {
            resultResp= new ResultResp<>(HttpStatus.BAD_REQUEST);
        }
        return resultResp;
    }

    @GetMapping

    @PostMapping("post/create/a")
    public ResultResp<?> createA(@RequestParam String suburbNames,
                                 @RequestParam String postcodes) {
        ResultResp<?> resultResp;
        try {
            resultResp = new ResultResp<>(HttpStatus.OK, postDetailService.save(new PostDetailDTO(postcodes, suburbNames)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultResp;
    }


    @PostMapping("post/{id}")
    public ResultResp<?> getOne(@PathVariable Long id) {
        return new ResultResp<>(HttpStatus.OK, postDetailRepository.getOne(id));
    }

}
