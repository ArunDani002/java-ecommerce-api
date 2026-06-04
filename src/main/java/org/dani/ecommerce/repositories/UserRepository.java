    package org.dani.ecommerce.repositories;

    import org.dani.ecommerce.models.UserModel;
    import org.springframework.data.jpa.repository.JpaRepository;

    import java.util.Optional;

    public interface UserRepository extends JpaRepository<UserModel, Long> {
        Optional<UserModel> findByEmail(String email);
        Optional<UserModel> findByUuid(String uuid);
    }
