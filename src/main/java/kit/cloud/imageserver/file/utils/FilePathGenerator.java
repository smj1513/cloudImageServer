package kit.cloud.imageserver.file.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class FilePathGenerator {
	public String generatePath(String saveName) {
		LocalDateTime now = LocalDateTime.now();
		String year = String.valueOf(now.getYear());
		String month = String.valueOf(now.getMonthValue());
		String day = String.valueOf(now.getDayOfMonth());

		StringBuilder pathBuilder = new StringBuilder();
		pathBuilder
				.append(year).append("/")
				.append(month).append("/")
				.append(day).append("/")
				.append(saveName);
		return pathBuilder.toString();
	}


	public String generateSaveFileName(String originFileName) {
		//파일 확장자
		String extension = originFileName.substring(originFileName.lastIndexOf("."));
		return UUID.randomUUID().toString().replace("-", "") + extension;
	}
}
