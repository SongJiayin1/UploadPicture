package xjtu.controller;

import xjtu.bean.Picture;
import xjtu.service.PictureService;
import xjtu.service.XmlService;
import xjtu.storage.StorageFileNotFoundException;
import xjtu.storage.StorageService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {

    private final StorageService storageService;
    private PictureService pictureService;
    private XmlService xmlService;

    @Autowired
    public FileUploadController(StorageService storageService, PictureService pictureService, XmlService xmlService) {
        this.storageService = storageService;
        this.pictureService = pictureService;
        this.xmlService = xmlService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "mutiUploadForm";
    }


    @GetMapping("/display")
    public String displayPicture(Model model) throws IOException {
        return "displayPicture";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws DocumentException {

        System.out.println("file.getname():" + file.getOriginalFilename());
        //String filenames = file.getOriginalFilename();
        //String[] names = filenames.split("\n");
        //System.out.println("start");
        //for (String name: names) {
         //   System.out.println("filename" + name);
       // }
        //System.out.println("end");
        if (file.getContentType().equals("text/xml")) {
            SAXReader reader = new SAXReader();
            Document document = null;
            try {
                document = reader.read(file.getInputStream());
            } catch(IOException e) {
                System.out.println(e.getStackTrace());
            }
            if (document != null) {
                Element root = document.getRootElement();
                Element efilename = root.element("filename");
                String filename = efilename.getText().substring(0, efilename.getText().length() - 4);
                if (pictureService.findByFilename(filename) != null) {
                    pictureService.xmlUpdate(filename);
                } else {
                    pictureService.xmlInsert(filename);
                }
                Picture picture = pictureService.findByFilename(filename);
                if (picture != null) {
                    List<Element> childs = root.elements();
                    for (Element child : childs) {
                        if (child.getName().equals("object")) {
                            Element type = child.element("name");
                            xmlService.insert(picture.getId(), type.getText());
                        }
                    }
                }
                //System.out.println("test: " + filename + " " + type.getText());
            }
        }
        if (file.getContentType().equals("image/bmp")) {
            String filename = file.getOriginalFilename();
            filename = filename.substring(0, filename.length() - 4);
            if (pictureService.findByFilename(filename) != null) {
                pictureService.picUpdate(filename);
                System.out.println("you");
            } else {
                pictureService.picInsert(filename);
                System.out.println("meiyou");
            }
        }
        System.out.println(file.getContentType());
            System.out.println("yes");
        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}