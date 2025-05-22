package com.mapzilla.backend.feature.user.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mapzilla.backend.feature.favouritePlace.model.FavouritePlace;
import com.mapzilla.backend.feature.history.model.History;
import com.mapzilla.backend.feature.user.constants.UserConstants;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@NamedQuery(name = UserConstants.FIND_USER_BY_EMAIL,
        query = "SELECT u FROM User u WHERE u.email = :email"
)
@NamedQuery(name = UserConstants.FIND_ALL_USERS_EXCEPT_SELF,
        query = "SELECT u FROM User u WHERE u.id != :publicId")
@NamedQuery(name = UserConstants.FIND_USER_BY_PUBLIC_ID,
        query = "SELECT u FROM User u WHERE u.id = :publicId")
public class User {

    private static final int LAST_ACTIVATE_INTERVAL = 5;

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private History history;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<FavouritePlace> favouritePlaces = new ArrayList<>();
    private LocalDateTime lastSeen;

    @Transient
    public boolean isUserOnline() {
        return lastSeen != null && lastSeen.isAfter(LocalDateTime.now().minusMinutes(LAST_ACTIVATE_INTERVAL));
    }
}
