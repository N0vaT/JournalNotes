package ru.nova.notesapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "jn_check_list_item")
public class CheckListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "item_status")
    @Enumerated(value = EnumType.STRING)
    private ItemStatus itemStatus;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "check_list_id")
    @JsonIgnore
    private CheckList checkList;
    public enum ItemStatus{
        WAITING, DENIED, COMPLETED
    }
}
