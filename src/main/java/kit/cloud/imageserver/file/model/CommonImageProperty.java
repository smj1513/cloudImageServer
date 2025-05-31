package kit.cloud.imageserver.file.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class CommonImageProperty extends FileProperty{

	@Override
	public void clear() {

	}
}
