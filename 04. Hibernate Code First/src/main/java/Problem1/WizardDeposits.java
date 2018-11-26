package Problem1;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "wizard_deposits")
public class WizardDeposits {

    private int id;
    private String first_name;
    private String last_name;
    private String notes;
    private int age;
    private String magic_wand_creator;
    private int magic_wand_size;
    private String deposit_group;
    private LocalDate deposit_start_date;
    private Double deposit_amount;
    private Double deposit_interest;
    private Double deposit_charge;
    private LocalDate deposit_expiration_date;
    private Boolean is_deposit_expired;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column(name = "first_name",length = 50)
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    @Column(name = "last_name",length = 60)//,nullable = false)
    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    @Column(name = "notes",length = 1000)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    //@Column(nullable = false)
    public int getAge() {
        return age;
    }
    @Column(name = "magic_wand_creator",length = 100)
    public String getMagic_wand_creator() {
        return magic_wand_creator;
    }

    public void setMagic_wand_creator(String magic_wand_creator) {
        this.magic_wand_creator = magic_wand_creator;
    }
    @Column(name = "magic_wand_size")
    public int getMagic_wand_size() {
        return magic_wand_size;
    }

    public void setMagic_wand_size(int magic_wand_size) {
        this.magic_wand_size = magic_wand_size;
    }
    @Column(name = "deposit_group",length = 20)
    public String getDeposit_group() {
        return deposit_group;
    }

    public void setDeposit_group(String deposit_group) {
        this.deposit_group = deposit_group;
    }
    @Column(name = "deposit_start_date")
    public LocalDate getDeposit_start_date() {
        return deposit_start_date;
    }

    public void setDeposit_start_date(LocalDate deposit_start_date) {
        this.deposit_start_date = deposit_start_date;
    }
    @Column(name = "deposit_amount")
    public Double getDeposit_amount() {
        return deposit_amount;
    }

    public void setDeposit_amount(Double deposit_amount) {
        this.deposit_amount = deposit_amount;
    }
    @Column(name = "deposit_interest")
    public Double getDeposit_interest() {
        return deposit_interest;
    }

    public void setDeposit_interest(Double deposit_interest) {
        this.deposit_interest = deposit_interest;
    }
    @Column(name = "deposit_charge")
    public Double getDeposit_charge() {
        return deposit_charge;
    }

    public void setDeposit_charge(Double deposit_charge) {
        this.deposit_charge = deposit_charge;
    }
    @Column(name = "deposit_expiration_date")
    public LocalDate getDeposit_expiration_date() {
        return deposit_expiration_date;
    }

    public void setDeposit_expiration_date(LocalDate deposit_expiration_date) {
        this.deposit_expiration_date = deposit_expiration_date;
    }
    @Column(name = "is_deposit_expired")
    public Boolean getIs_deposit_expired() {
        return is_deposit_expired;
    }

    public void setIs_deposit_expired(Boolean is_deposit_expired) {
        this.is_deposit_expired = is_deposit_expired;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be less than 0!");
        }
        this.age = age;
    }
}
