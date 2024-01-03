package com.dormitory.controller;

import com.dormitory.dto.*;
import com.dormitory.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;
import com.dormitory.dto.MemberDTO;
import com.dormitory.dto.ReservationDTO;
import com.dormitory.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000/"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class RestMenuController {
    private final MemberService service;


    //1. 회원 정보 조회 -> OK
    @ResponseBody
    @GetMapping("/memberInfo")
    public MemberDTO getMemberInfo(@RequestParam("userid") String userid) throws Exception {
        return service.getMemberInfo(userid);
    }

    //2. 예약 정보 조회 -> OK
    @GetMapping("/reservationInfo")
    public List<ReservationDTO> getReservationInfo(@RequestParam("userid") String userid) throws Exception {

        return service.getReservationInfoById(userid);
    }

    //3. 예약 상태 변경
    @ResponseBody
    @PostMapping("/reservationInfo")
    public String postReservationInfo(@RequestParam("reservation_code") int reservation_code) throws Exception {

        //예약내역 상태 변경
        service.ReservationOut(reservation_code);

        return "{\"message\":\"GOOD\"}";
    }
    //4. 방문 내역 -> OK
    @GetMapping("/visited")
    public List<ReservationDTO> getVisited(@RequestParam("userid") String userid) throws Exception {

        return service.getVisited(userid);
    }



    //5. 방문내역 삭제
    @Transactional
    @ResponseBody
    @PostMapping("/visited")
    public String postVisited(ReservationDTO reservation,@RequestParam("reservation_code") int reservation_code) throws Exception {
            //방문 내역 삭제
            service.DeleteVisited(reservation_code);
            return "{\"message\":\"GOOD\"}";
    }
    //6. 방문내역 리뷰등록
    @PostMapping("/review")
    public void postReview(@RequestBody ReviewDTO review) throws Exception {
//        System.out.println(reservation.getReservation_code());
//        System.out.println(reservation.getD_code());
//        System.out.println(reservation.getR_code());
//        System.out.println(reservation.getM_telno());
        System.out.println(review.getReview_comment());
        System.out.println(review.getReview_score());
        //리뷰 등록하기
        service.setReview(review);
    }
    //7. 사용자가 작성한 리뷰 조회
    @GetMapping("/memberReview")
    public List<ReviewDTO> getMemberReview() throws Exception {
        String userid = "ehrud0226@gmail.com";
        List<ReviewDTO> list = service.getMemberReview(userid);



        // 각 방의 상세 정보와 예약 상태를 매핑
        for (ReviewDTO reviewList :  list) {
           /* Map<String, List<FileDTO>> map = new HashMap<>();*/

            List<FileDTO> li = service.imagesInfoview(reviewList.getReview_code());
            /*map.put("File", li);*/

            reviewList.setFileInfo(li);
        }

        return list;
       /*return service.getMemberReview(userid);*/
    }
    /*        if(kind.equals("I")) { //게시물 등록
            service.setReview(review);
        }*/

    /*        if(kind.equals("U")) { //게시물 수정
                service.modify(board);
                seqno = board.getSeqno();

                if(deleteFileList != null) {
                    for(int i=0; i<deleteFileList.length; i++) {
                        service.deleteFileList(deleteFileList[i]);
                    }
                }
            }*/
    //8. 이미지 파일 등록
    @PostMapping("/imageUpload")
    public void postImageUpload(FileDTO file,ReviewDTO review,@RequestParam("kind") String kind,
                                @RequestParam(name="sendToFileList",required = false) List<MultipartFile> multipartFile,
                                @RequestParam(name="deleteFileList",required=false) int[] deleteFileList) throws Exception{

        // 이미 deleteFileList는 문자열 배열이므로 추가 변환 없이 사용할 수 있습니다.
        /*int[] deleteFileIntArray = Arrays.stream(deleteFileList)
                .mapToInt(Integer::parseInt)
                .toArray();*/
        System.out.println(Arrays.toString(deleteFileList) + " - delete");
      //  System.out.println(multipartFile);
        String path = "c:\\Repository\\file\\";
        //리뷰 등록
      //  System.out.println("review"+review.getReview_comment());
//        System.out.println("review2"+review.getReview_code());
       // System.out.println("review3"+review.getM_userid());

     //  String review_code = service.getReviewCode();
        if(kind.equals("I")){ //리뷰 작성
            service.setReview(review);
        }

        if(kind.equals("U")) { // 리뷰 수정

            service.modifyReview(review);
            if(deleteFileList != null) {
                for(int i=0; i<deleteFileList.length; i++) {
                    System.out.println(deleteFileList[i]);
                    System.out.println();
                    service.deleteFileList(deleteFileList[i]);
                }
            }
        }

        //파일 등록
        if(multipartFile != null && !multipartFile.isEmpty()) {
            File targetFile = null;
            Map<String,Object> imageFile = null;
            List<String> existingStoredFiles = service.getFilename(); // 데이터베이스에서 저장된 review_stored 값들을 가져옵니다.

            for(MultipartFile mpr:multipartFile) {


                String review_file = mpr.getOriginalFilename();
                if(!existingStoredFiles.contains(review_file)) {
                    String review_fileExtension = review_file.substring(review_file.lastIndexOf("."));
                    String review_stored = UUID.randomUUID().toString().replaceAll("-", "") + review_fileExtension;
                    long review_filesize = mpr.getSize();

                    try {
                        targetFile = new File(path + review_stored);
                        mpr.transferTo(targetFile);
                        imageFile = new HashMap<>();
                        imageFile.put("review_code", review.getReview_code());
                        imageFile.put("review_file", review_file);
                        imageFile.put("review_stored", review_stored);
                        imageFile.put("review_filesize", review_filesize);
                        imageFile.put("userid", review.getM_userid());
                        imageFile.put("checkfile", "Y");//Y : 파일 존재, N : 파일 삭제
                        /*                    imageFile.put("file_img",file_img);*/
                        //imageFile.put("review_code",review_code);

                        service.setImages(imageFile);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("Duplicate file detected. Skipping upload.");
                }
                }

            }
        else {
            // multipartFile이 null이거나 비어있는 경우의 처리 (필요에 따라 추가하세요)
            System.out.println("multipartFile is null or empty.");
        }
    }
    // 데이터베이스에서 저장된 review_stored 값들을 가져오는 메서드 (예시)
//    private List<String> getExistingStoredFiles() throws Exception {
//        // 여기에서는 서비스 레이어나 DAO 레이어를 통해 데이터베이스에서 review_stored 값들을 가져옵니다.
//        // 실제 구현은 데이터베이스 연동 방식과 구조에 따라 달라질 수 있습니다.
//        return service.getFilename();
//    }
}
