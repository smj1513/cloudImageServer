package kit.cloud.imageserver.file.application;

import kit.cloud.imageserver.file.interfaces.response.FileResponse;
import kit.cloud.imageserver.file.model.FileProperty;
import kit.cloud.imageserver.file.repository.FilePropertyRepository;
import kit.cloud.imageserver.file.utils.FileStorage;
import kit.cloud.imageserver.file.utils.ImageType;
import kit.cloud.imageserver.file.utils.factory.FilePropertyFactory;
import kit.cloud.imageserver.file.utils.factory.FilePropertyProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FileAppService {
	private final FilePropertyProvider filePropertyProvider;
	private final FileStorage fileStorage;
	private final FilePropertyFactory filePropertyFactory;
	private final FilePropertyRepository filePropertyRepository;

	public List<FileResponse> getAllImages() {
		return filePropertyRepository.findAll().stream().map(FileResponse::from).toList();
	}

	public FileResponse uploadImage(MultipartFile file) {
		return uploadImage(file, ImageType.COMMON);
	}
	public FileResponse uploadImage(MultipartFile file, ImageType imageType){
		FileProperty fileProperty = filePropertyProvider.create(file.getOriginalFilename(), file.getContentType(), file.getSize(), imageType);
		fileStorage.store(file, fileProperty.getPath());
		filePropertyRepository.save(fileProperty);
		return FileResponse.from(fileProperty);
	}

	public FileResponse deleteFile(Long filePropertyId) {
		FileProperty fileProperty = filePropertyRepository.findById(filePropertyId).orElseThrow(() -> new RuntimeException("파일을 찾을 수 없습니다."));
		String path = fileProperty.getPath();

		fileStorage.delete(path);
		//연관 관계를 해제 시켜줘야 fileProperty가 삭제됨
		fileProperty.clear();
		filePropertyRepository.delete(fileProperty);
		filePropertyRepository.flush();

		return FileResponse.from(fileProperty);
	}
}
