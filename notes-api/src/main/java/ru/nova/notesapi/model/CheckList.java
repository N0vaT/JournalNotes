package ru.nova.notesapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "jn_check_list")
public class CheckList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_list_id")
    private Long checkListId;
    @Column(name = "check_list_title")
    private String title;
    @OneToMany(mappedBy = "checkList")
    private List<CheckListItem> checkListItems;
    @OneToOne(mappedBy = "checkList")
    @JsonIgnore
    private Note note;
}
