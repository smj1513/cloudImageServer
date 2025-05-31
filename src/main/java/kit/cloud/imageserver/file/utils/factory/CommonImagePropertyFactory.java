package kit.cloud.imageserver.file.utils.factory;

import kit.cloud.imageserver.file.model.CommonImageProperty;
import kit.cloud.imageserver.file.model.FileProperty;
import kit.cloud.imageserver.file.utils.ImageType;
import org.springframework.stereotype.Component;

@Component
public class CommonImagePropertyFactory implements FilePropertyFactory{
	@Override
	public FileProperty create(String originFileName, String saveFileName, String path, String contentType, Long size, ImageType type) {
		return CommonImageProperty.builder()
				.originFileName(originFileName)
				.savedFileName(saveFileName)
				.contentType(contentType)
				.size(size)
				.path(path)
				.build();
	}

	@Override
	public boolean supports(ImageType imageType) {
		return ImageType.COMMON.equals(imageType);
	}
}
