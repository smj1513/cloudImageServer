package kit.cloud.imageserver.file.interfaces.response;

import kit.cloud.imageserver.file.model.FileProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse {
	private Long id;
	private String name;
	private String contentType;
	private Long size;
	private String path;

	public static FileResponse from(FileProperty fileProperty) {
		return fileProperty == null ? null :
				FileResponse.builder()
						.id(fileProperty.getId())
						.name(fileProperty.getOriginFileName())
						.contentType(fileProperty.getContentType()).size(fileProperty.getSize())
						.path(fileProperty.getPath()).build();
	}
}

