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

    @ElDtoField(logicalName = "공고정보", physicalName = "sendEmailInfoListVo", type = "", typeKind = "List", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private java.util.List<com.demo.proworks.domain.post.vo.SendEmailInfoListVo> sendEmailInfoListVo;

    @ElDtoField(logicalName = "합불여부", physicalName = "isPassed", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String isPassed;

    @ElDtoField(logicalName = "메일 내용", physicalName = "emailContent", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String emailContent;

    @ElVoField(physicalName = "sendEmailInfoListVo")
    public java.util.List<com.demo.proworks.domain.post.vo.SendEmailInfoListVo> getSendEmailInfoListVo(){
        return sendEmailInfoListVo;
    }

    @ElVoField(physicalName = "sendEmailInfoListVo")
    public void setSendEmailInfoListVo(java.util.List<com.demo.proworks.domain.post.vo.SendEmailInfoListVo> sendEmailInfoListVo){
        this.sendEmailInfoListVo = sendEmailInfoListVo;
    }

    @ElVoField(physicalName = "isPassed")
    public String getIsPassed(){
        String ret = this.isPassed;
        return ret;
    }

    @ElVoField(physicalName = "isPassed")
    public void setIsPassed(String isPassed){
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
        sb.append("sendEmailInfoListVo").append("=").append(sendEmailInfoListVo).append(",");
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
        for( int i=0 ; sendEmailInfoListVo != null && i < sendEmailInfoListVo.size() ; i++ ) {
            com.demo.proworks.domain.post.vo.SendEmailInfoListVo vo = (com.demo.proworks.domain.post.vo.SendEmailInfoListVo)sendEmailInfoListVo.get(i);
            vo._xStreamEnc();	 
        }
    }


    @Override
    public void _xStreamDec() {
        for( int i=0 ; sendEmailInfoListVo != null && i < sendEmailInfoListVo.size() ; i++ ) {
            com.demo.proworks.domain.post.vo.SendEmailInfoListVo vo = (com.demo.proworks.domain.post.vo.SendEmailInfoListVo)sendEmailInfoListVo.get(i);
            vo._xStreamDec();	 
        }
    }


}
