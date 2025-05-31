package kit.cloud.imageserver.file.repository;

import kit.cloud.imageserver.file.model.FileProperty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilePropertyRepository extends JpaRepository<FileProperty,Long> {
}
