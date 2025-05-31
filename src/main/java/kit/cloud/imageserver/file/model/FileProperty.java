package kit.cloud.imageserver.file.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class FileProperty {

	@Id
	@GeneratedValue
	private Long id;
	private String originFileName;
	private String savedFileName;
	private String contentType;
	private Long size;
	private String path;
	private LocalDateTime createdAt;

	public abstract void clear();
}
