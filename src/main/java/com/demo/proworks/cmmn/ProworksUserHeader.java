package com.demo.proworks.cmmn;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import com.inswave.elfw.exception.ElException;
import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.inswave.elfw.log.AppLog;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "Y", delimeterYn = "", logicalName = "헤더VO")
public class ProworksUserHeader extends com.inswave.elfw.core.UserHeader {
    private static final long serialVersionUID = 1L;

    private int _offset;

    public ProworksUserHeader(){
        this._offset = 0;
    }

    public ProworksUserHeader(int iOffset){
        this._offset = iOffset;
    }

    @ElDtoField(logicalName = "전문길이", physicalName = "fldLen", type = "int", typeKind = "", fldYn = "Yes", delimeterYn = "Yes", cryptoGbn = "", cryptoKind = "", length = 10, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int fldLen;

    @ElDtoField(logicalName = "서비스ID", physicalName = "svcId", type = "String", typeKind = "", fldYn = "Yes", delimeterYn = "Yes", cryptoGbn = "", cryptoKind = "", length = 20, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String svcId;

    @ElDtoField(logicalName = "전문입력ID", physicalName = "inInfId", type = "String", typeKind = "", fldYn = "Yes", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 30, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String inInfId;

    @ElDtoField(logicalName = "전문출력ID", physicalName = "outInfId", type = "String", typeKind = "", fldYn = "Yes", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 30, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String outInfId;

    @ElDtoField(logicalName = "성공실패여부", physicalName = "sucYn", type = "String", typeKind = "", fldYn = "Yes", delimeterYn = "Yes", cryptoGbn = "", cryptoKind = "", length = 1, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String sucYn;

    @ElDtoField(logicalName = "에러코드", physicalName = "errorCode", type = "String", typeKind = "", fldYn = "Yes", delimeterYn = "Yes", cryptoGbn = "", cryptoKind = "", length = 30, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String errorCode;

    @ElDtoField(logicalName = "에러메시지", physicalName = "errMag", type = "String", typeKind = "", fldYn = "Yes", delimeterYn = "Yes", cryptoGbn = "", cryptoKind = "", length = 100, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String errMag;

    @ElDtoField(logicalName = "테스트", physicalName = "location", type = "String", typeKind = "", fldYn = "Yes", delimeterYn = "Yes", cryptoGbn = "", cryptoKind = "", length = 10, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String location;

    @ElDtoField(logicalName = "사용자ID", physicalName = "testId", type = "String", typeKind = "", fldYn = "Yes", delimeterYn = "Yes", cryptoGbn = "", cryptoKind = "", length = 10, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String testId;

    @ElDtoField(logicalName = "테스트01 카운트", physicalName = "test01Cnt", type = "String", typeKind = "", fldYn = "No", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 11, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String test01Cnt;

    @ElDtoField(logicalName = "테스트01유저헤더", physicalName = "test01UserHeader", type = "", typeKind = "List", fldYn = "No", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "test01Cnt", desc = "", attr = "")
    private java.util.List<com.demo.proworks.cmmn.Test01UserHeader> test01UserHeader;

    @ElDtoField(logicalName = "테스트 유저헤더 02", physicalName = "test02UserHeader", type = "", typeKind = "List", fldYn = "No", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private java.util.List<com.demo.proworks.cmmn.Test02UserHeader> test02UserHeader;

    @ElDtoField(logicalName = "사용자이름", physicalName = "testUserName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String testUserName;

    @ElDtoField(logicalName = "부서명", physicalName = "testDeptName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String testDeptName;

    @ElDtoField(logicalName = "부서코드", physicalName = "testDeptNo", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String testDeptNo;

    @ElDtoField(logicalName = "이메일", physicalName = "email", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String email;

    @ElVoField(physicalName = "fldLen")
    public int getFldLen(){
        return fldLen;
    }

    @ElVoField(physicalName = "fldLen")
    public void setFldLen(int fldLen){
        this.fldLen = fldLen;
    }

    @ElVoField(physicalName = "svcId")
    public String getSvcId(){
        String ret = this.svcId;
        return ret;
    }

    @ElVoField(physicalName = "svcId")
    public void setSvcId(String svcId){
        this.svcId = svcId;
    }

    @ElVoField(physicalName = "inInfId")
    public String getInInfId(){
        String ret = this.inInfId;
        return ret;
    }

    @ElVoField(physicalName = "inInfId")
    public void setInInfId(String inInfId){
        this.inInfId = inInfId;
    }

    @ElVoField(physicalName = "outInfId")
    public String getOutInfId(){
        String ret = this.outInfId;
        return ret;
    }

    @ElVoField(physicalName = "outInfId")
    public void setOutInfId(String outInfId){
        this.outInfId = outInfId;
    }

    @ElVoField(physicalName = "sucYn")
    public String getSucYn(){
        String ret = this.sucYn;
        return ret;
    }

    @ElVoField(physicalName = "sucYn")
    public void setSucYn(String sucYn){
        this.sucYn = sucYn;
    }

    @ElVoField(physicalName = "errorCode")
    public String getErrorCode(){
        String ret = this.errorCode;
        return ret;
    }

    @ElVoField(physicalName = "errorCode")
    public void setErrorCode(String errorCode){
        this.errorCode = errorCode;
    }

    @ElVoField(physicalName = "errMag")
    public String getErrMag(){
        String ret = this.errMag;
        return ret;
    }

    @ElVoField(physicalName = "errMag")
    public void setErrMag(String errMag){
        this.errMag = errMag;
    }

    @ElVoField(physicalName = "location")
    public String getLocation(){
        String ret = this.location;
        return ret;
    }

    @ElVoField(physicalName = "location")
    public void setLocation(String location){
        this.location = location;
    }

    @ElVoField(physicalName = "testId")
    public String getTestId(){
        String ret = this.testId;
        return ret;
    }

    @ElVoField(physicalName = "testId")
    public void setTestId(String testId){
        this.testId = testId;
    }

    @ElVoField(physicalName = "test01Cnt")
    public String getTest01Cnt(){
        String ret = this.test01Cnt;
        return ret;
    }

    @ElVoField(physicalName = "test01Cnt")
    public void setTest01Cnt(String test01Cnt){
        this.test01Cnt = test01Cnt;
    }

    @ElVoField(physicalName = "test01UserHeader")
    public java.util.List<com.demo.proworks.cmmn.Test01UserHeader> getTest01UserHeader(){
        return test01UserHeader;
    }

    @ElVoField(physicalName = "test01UserHeader")
    public void setTest01UserHeader(java.util.List<com.demo.proworks.cmmn.Test01UserHeader> test01UserHeader){
        this.test01UserHeader = test01UserHeader;
    }

    @ElVoField(physicalName = "test02UserHeader")
    public java.util.List<com.demo.proworks.cmmn.Test02UserHeader> getTest02UserHeader(){
        return test02UserHeader;
    }

    @ElVoField(physicalName = "test02UserHeader")
    public void setTest02UserHeader(java.util.List<com.demo.proworks.cmmn.Test02UserHeader> test02UserHeader){
        this.test02UserHeader = test02UserHeader;
    }

    @ElVoField(physicalName = "testUserName")
    public String getTestUserName(){
        String ret = this.testUserName;
        return ret;
    }

    @ElVoField(physicalName = "testUserName")
    public void setTestUserName(String testUserName){
        this.testUserName = testUserName;
    }

    @ElVoField(physicalName = "testDeptName")
    public String getTestDeptName(){
        String ret = this.testDeptName;
        return ret;
    }

    @ElVoField(physicalName = "testDeptName")
    public void setTestDeptName(String testDeptName){
        this.testDeptName = testDeptName;
    }

    @ElVoField(physicalName = "testDeptNo")
    public String getTestDeptNo(){
        String ret = this.testDeptNo;
        return ret;
    }

    @ElVoField(physicalName = "testDeptNo")
    public void setTestDeptNo(String testDeptNo){
        this.testDeptNo = testDeptNo;
    }

    @ElVoField(physicalName = "email")
    public String getEmail(){
        String ret = this.email;
        return ret;
    }

    @ElVoField(physicalName = "email")
    public void setEmail(String email){
        this.email = email;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProworksUserHeader [");
        sb.append("fldLen").append("=").append(fldLen).append(",");
        sb.append("svcId").append("=").append(svcId).append(",");
        sb.append("inInfId").append("=").append(inInfId).append(",");
        sb.append("outInfId").append("=").append(outInfId).append(",");
        sb.append("sucYn").append("=").append(sucYn).append(",");
        sb.append("errorCode").append("=").append(errorCode).append(",");
        sb.append("errMag").append("=").append(errMag).append(",");
        sb.append("location").append("=").append(location).append(",");
        sb.append("testId").append("=").append(testId).append(",");
        sb.append("test01Cnt").append("=").append(test01Cnt).append(",");
        sb.append("test01UserHeader").append("=").append(test01UserHeader).append(",");
        sb.append("test02UserHeader").append("=").append(test02UserHeader).append(",");
        sb.append("testUserName").append("=").append(testUserName).append(",");
        sb.append("testDeptName").append("=").append(testDeptName).append(",");
        sb.append("testDeptNo").append("=").append(testDeptNo).append(",");
        sb.append("email").append("=").append(email);
        sb.append("]");
        return sb.toString();

    }

    public boolean isFixedLengthVo() {
        return true;
    }

    public byte[] marshalFld() throws IOException{
        return marshalFld( com.inswave.elfw.ElConfig.getFldEncode() ); 
    }

	public byte[] marshalFld(String encode) throws IOException{
    	ByteArrayOutputStream bout = new ByteArrayOutputStream();
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(bout);
            out.write( com.inswave.elfw.util.TypeConversionUtil.intToBytes(this.fldLen , 10) );
            out.write( com.inswave.elfw.util.TypeConversionUtil.strToSpBytes(this.svcId , 20, encode ) );
            out.write( com.inswave.elfw.util.TypeConversionUtil.strToSpBytes(this.inInfId , 30, encode ) );
            out.write( com.inswave.elfw.util.TypeConversionUtil.strToSpBytes(this.outInfId , 30, encode ) );
            out.write( com.inswave.elfw.util.TypeConversionUtil.strToSpBytes(this.sucYn , 1, encode ) );
            out.write( com.inswave.elfw.util.TypeConversionUtil.strToSpBytes(this.errorCode , 30, encode ) );
            out.write( com.inswave.elfw.util.TypeConversionUtil.strToSpBytes(this.errMag , 100, encode ) );
            out.write( com.inswave.elfw.util.TypeConversionUtil.strToSpBytes(this.location , 10, encode ) );
            out.write( com.inswave.elfw.util.TypeConversionUtil.strToSpBytes(this.testId , 10, encode ) );
        } catch (IOException e) {
                AppLog.error("marshalFld Error:["+ toString()+"]", e);
                throw e;
        } finally {
            try	{
                if (out != null) out.close();
           } catch (IOException ie) {
                AppLog.error("marshalFld out close Error", ie);
           }
            try	{
                if (bout != null) bout.close();
           } catch (IOException ie) {
                AppLog.error("marshalFld bout close Error", ie);
           }
        }
        return bout.toByteArray();
    }

    public void unMarshalFld( byte[] bytes ) throws ElException{
        unMarshalFld( bytes, com.inswave.elfw.ElConfig.getFldEncode() ); 
    }

    public void unMarshalFld( byte[] bytes , String encode) throws ElException{
        try{ 
             this.fldLen = com.inswave.elfw.util.TypeConversionUtil.bytesToInt( bytes, _offset, 10, encode );
             _offset += 10;
            this.svcId = com.inswave.elfw.util.TypeConversionUtil.getTrimmedString( bytes, _offset, 20, encode );
             _offset += 20;
            this.inInfId = com.inswave.elfw.util.TypeConversionUtil.getTrimmedString( bytes, _offset, 30, encode );
             _offset += 30;
            this.outInfId = com.inswave.elfw.util.TypeConversionUtil.getTrimmedString( bytes, _offset, 30, encode );
             _offset += 30;
            this.sucYn = com.inswave.elfw.util.TypeConversionUtil.getTrimmedString( bytes, _offset, 1, encode );
             _offset += 1;
            this.errorCode = com.inswave.elfw.util.TypeConversionUtil.getTrimmedString( bytes, _offset, 30, encode );
             _offset += 30;
            this.errMag = com.inswave.elfw.util.TypeConversionUtil.getTrimmedString( bytes, _offset, 100, encode );
             _offset += 100;
            this.location = com.inswave.elfw.util.TypeConversionUtil.getTrimmedString( bytes, _offset, 10, encode );
             _offset += 10;
            this.testId = com.inswave.elfw.util.TypeConversionUtil.getTrimmedString( bytes, _offset, 10, encode );
             _offset += 10;
        }catch(ElException e) { 
            String errorLine = com.inswave.elfw.util.TypeConversionUtil.getTrimmedString( bytes, 0, bytes.length, encode );
            AppLog.error("unMarshalFld Error:["+ errorLine+"]", e);
            throw e;
        } 
    }

    public int getOffset(){
        return _offset;
    }

    public int getFixedTotalLength(){
        return 241;
    }

    @Override
    public void _xStreamEnc() {
        for( int i=0 ; test01UserHeader != null && i < test01UserHeader.size() ; i++ ) {
            com.demo.proworks.cmmn.Test01UserHeader vo = (com.demo.proworks.cmmn.Test01UserHeader)test01UserHeader.get(i);
            vo._xStreamEnc();	 
        }
        for( int i=0 ; test02UserHeader != null && i < test02UserHeader.size() ; i++ ) {
            com.demo.proworks.cmmn.Test02UserHeader vo = (com.demo.proworks.cmmn.Test02UserHeader)test02UserHeader.get(i);
            vo._xStreamEnc();	 
        }
    }


    @Override
    public void _xStreamDec() {
        for( int i=0 ; test01UserHeader != null && i < test01UserHeader.size() ; i++ ) {
            com.demo.proworks.cmmn.Test01UserHeader vo = (com.demo.proworks.cmmn.Test01UserHeader)test01UserHeader.get(i);
            vo._xStreamDec();	 
        }
        for( int i=0 ; test02UserHeader != null && i < test02UserHeader.size() ; i++ ) {
            com.demo.proworks.cmmn.Test02UserHeader vo = (com.demo.proworks.cmmn.Test02UserHeader)test02UserHeader.get(i);
            vo._xStreamDec();	 
        }
    }


}
