package kit.cloud.imageserver.file.utils.factory;

import kit.cloud.imageserver.file.model.CommonImageProperty;
import kit.cloud.imageserver.file.model.FileProperty;
import kit.cloud.imageserver.file.utils.FilePathGenerator;
import kit.cloud.imageserver.file.utils.ImageType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FilePropertyProvider {

	private final List<FilePropertyFactory> filePropertyFactories;
	private final FilePathGenerator filePathGenerator;

	@Autowired
	public FilePropertyProvider(CommonImagePropertyFactory commonImagePropertyFactory, FilePathGenerator filePathGenerator){
		this.filePropertyFactories = List.of(commonImagePropertyFactory);
		this.filePathGenerator = filePathGenerator;
	}

	public FileProperty create(String fileName, String contentType, Long size, ImageType imageType) {
		String savedFile = filePathGenerator.generateSaveFileName(fileName);
		String path = filePathGenerator.generatePath(savedFile);

		for (FilePropertyFactory factory : filePropertyFactories) {
			if (factory.supports(imageType)) {
				return factory.create(fileName, savedFile, path, contentType, size, imageType);
			}
		}
		throw new RuntimeException("지원하지 않는 파일 타입입니다.");
	}
}
