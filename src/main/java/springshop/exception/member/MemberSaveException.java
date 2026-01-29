package springshop.exception.member;

import lombok.Getter;

@Getter
public class MemberSaveException extends MemberException {

    private final String memberId;

    public MemberSaveException(String memberId, String message) {
        super(message);
        this.memberId = memberId;
    }

}
