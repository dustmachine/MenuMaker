package com.northwoodsfoundry.menumaker.repositories;

import com.northwoodsfoundry.menumaker.model.DayMenu;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableScan
@Repository
public interface MenuRepository extends CrudRepository<DayMenu, String> {

    @NonNull
    Optional<DayMenu> findById(@NonNull String id);

}

