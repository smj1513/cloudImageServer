package kit.cloud.imageserver.file.utils;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorage {
	void store(MultipartFile file, String path);
	void delete(String path);
}
