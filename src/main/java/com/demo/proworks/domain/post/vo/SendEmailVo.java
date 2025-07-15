package com.demo.proworks.domain.post.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "공고정보")
public class SendEmailVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public SendEmailVo(){
    }

    @ElDtoField(logicalName = "이메일 리스트", physicalName = "emailListVo", type = "", typeKind = "List", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private java.util.List<com.demo.proworks.domain.post.vo.EmailListVo> emailListVo;

    @ElDtoField(logicalName = "합불여부", physicalName = "isPassed", type = "boolean", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private boolean isPassed;

    @ElDtoField(logicalName = "메일 내용", physicalName = "emailContent", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String emailContent;

    @ElVoField(physicalName = "emailListVo")
    public java.util.List<com.demo.proworks.domain.post.vo.EmailListVo> getEmailListVo(){
        return emailListVo;
    }

    @ElVoField(physicalName = "emailListVo")
    public void setEmailListVo(java.util.List<com.demo.proworks.domain.post.vo.EmailListVo> emailListVo){
        this.emailListVo = emailListVo;
    }

    @ElVoField(physicalName = "isPassed")
    public boolean isIsPassed(){
        return isPassed;
    }

    @ElVoField(physicalName = "isPassed")
    public void setIsPassed(boolean isPassed){
        this.isPassed = isPassed;
    }

    @ElVoField(physicalName = "emailContent")
    public String getEmailContent(){
        String ret = this.emailContent;
        return ret;
    }

    @ElVoField(physicalName = "emailContent")
    public void setEmailContent(String emailContent){
        this.emailContent = emailContent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SendEmailVo [");
        sb.append("emailListVo").append("=").append(emailListVo).append(",");
        sb.append("isPassed").append("=").append(isPassed).append(",");
        sb.append("emailContent").append("=").append(emailContent);
        sb.append("]");
        return sb.toString();

    }

    public boolean isFixedLengthVo() {
        return false;
    }

    @Override
    public void _xStreamEnc() {
        for( int i=0 ; emailListVo != null && i < emailListVo.size() ; i++ ) {
            com.demo.proworks.domain.post.vo.EmailListVo vo = (com.demo.proworks.domain.post.vo.EmailListVo)emailListVo.get(i);
            vo._xStreamEnc();	 
        }
    }


    @Override
    public void _xStreamDec() {
        for( int i=0 ; emailListVo != null && i < emailListVo.size() ; i++ ) {
            com.demo.proworks.domain.post.vo.EmailListVo vo = (com.demo.proworks.domain.post.vo.EmailListVo)emailListVo.get(i);
            vo._xStreamDec();	 
        }
    }


}
