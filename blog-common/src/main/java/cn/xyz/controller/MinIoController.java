package cn.xyz.controller;


import cn.xyz.util.JsonResult;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/minio")
public class MinIoController {

    private static final String ACCESSKEY = "admin";
    private static final String SECREKEY = "1Tsource2021.cn";
    private static final String ADDRESS = "http://minio.java.itsource.cn";
    private static final String BUCKETNAME = "java0820";


    //上传文件
    @PostMapping
    public JsonResult upload(@RequestPart("file") MultipartFile file) {
        try {
            // 构建一个Minio客户端
            MinioClient minioClient = MinioClient.builder()
                //创建容器时指定的账号
                .credentials(MinIoController.ACCESSKEY, MinIoController.SECREKEY)
                //上传地址
                .endpoint(MinIoController.ADDRESS).build();

            //处理文件名
            String oName = file.getOriginalFilename();
            String fileName = System.currentTimeMillis()+oName.substring(oName.lastIndexOf("."));

            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .object(fileName)	//文件名
                .bucket(MinIoController.BUCKETNAME)  //存储目录名
                .stream(file.getInputStream(), file.getSize(), -1).build(); //文件流，以及大小，-1代表不分片

            //执行上传
            minioClient.putObject(putObjectArgs);
            return JsonResult.success(fileName);

        } catch (Exception e) {
            return JsonResult.error("上传失败");
        }
    }

    //删除文件
    @DeleteMapping("/{name}")
    public JsonResult delete(@PathVariable("name")String name) {
        try {
            // 构建一个Minio客户端
            MinioClient minioClient = MinioClient.builder()
                //创建容器时指定的账号
                .credentials(MinIoController.ACCESSKEY, MinIoController.SECREKEY)
                //上传地址
                .endpoint(MinIoController.ADDRESS).build();

            minioClient.removeObject(MinIoController.BUCKETNAME, name);
            return JsonResult.success();
        } catch (Exception e) {
            return JsonResult.error("删除失败");
        }
    }
}