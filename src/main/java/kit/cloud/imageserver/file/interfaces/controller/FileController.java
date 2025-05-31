package kit.cloud.imageserver.file.interfaces.controller;

import kit.cloud.imageserver.file.application.FileAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class FileController {

	private final FileAppService fileService;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("images", fileService.getAllImages());
		return "gallery";
	}

	@GetMapping("/upload")
	public String uploadForm() {
		return "upload";
	}

	@PostMapping("/upload") // POST 요청과 /upload 경로를 매핑
	public ResponseEntity<String> handleFileUpload(@RequestPart("imageFiles") MultipartFile[] files) {
		if (files == null || files.length == 0) {
			return ResponseEntity.badRequest().body("업로드할 파일이 없습니다.");
		}

		try {
			for (MultipartFile file : files) {
				if (file.isEmpty()) {
					continue; // 빈 파일은 건너뜀
				}
				// 여기에 파일 저장 로직 구현
				fileService.uploadImage(file);
				System.out.println("Uploaded file: " + file.getOriginalFilename() + " size: " + file.getSize());
			}
			// 성공 응답
			return ResponseEntity.ok().body("파일 업로드 성공!");
		} catch (Exception e) {
			// 실패 응답
			e.printStackTrace();
			return ResponseEntity.status(500).body("파일 업로드 실패: " + e.getMessage());
		}
	}
}
