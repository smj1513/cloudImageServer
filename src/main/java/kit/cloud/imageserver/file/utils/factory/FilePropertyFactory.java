package kit.cloud.imageserver.file.utils.factory;

import kit.cloud.imageserver.file.model.FileProperty;
import kit.cloud.imageserver.file.utils.ImageType;

public interface FilePropertyFactory {
	FileProperty create(String originFileName, String saveFileName, String path, String contentType, Long size, ImageType type);

	boolean supports(ImageType imageType);
}
