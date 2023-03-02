package hospital.reservation.domain;

public enum Gender {
    남성("남성"), 여성("여성");

    
    //라디오버튼 사용시 작성
    private final String description;
    Gender(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
