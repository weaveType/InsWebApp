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
@ElDto(FldYn = "Y", delimeterYn = "", logicalName = "테스트01유저헤더")
public class Test01UserHeader extends com.inswave.elfw.core.UserHeader {
    private static final long serialVersionUID = 1L;

    private int _offset;

    public Test01UserHeader(){
        this._offset = 0;
    }

    public Test01UserHeader(int iOffset){
        this._offset = iOffset;
    }

    @ElDtoField(logicalName = "사원번호", physicalName = "empno", type = "int", typeKind = "", fldYn = "Yes", delimeterYn = "Yes", cryptoGbn = "", cryptoKind = "", length = 4, dotLen = 0, baseValue = "", desc = "사원번호 설명", attr = "")
    private int empno;

    @ElDtoField(logicalName = "사원명", physicalName = "ename", type = "String", typeKind = "", fldYn = "Yes", delimeterYn = "Yes", cryptoGbn = "", cryptoKind = "", length = 10, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String ename;

    @ElDtoField(logicalName = "직업", physicalName = "job", type = "String", typeKind = "", fldYn = "Yes", delimeterYn = "Yes", cryptoGbn = "", cryptoKind = "", length = 9, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String job;

    @ElDtoField(logicalName = "직속상사번호", physicalName = "mgr", type = "int", typeKind = "", fldYn = "Yes", delimeterYn = "Yes", cryptoGbn = "", cryptoKind = "", length = 4, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int mgr;

    @ElDtoField(logicalName = "입사일", physicalName = "hiredate", type = "String", typeKind = "", fldYn = "Yes", delimeterYn = "Yes", cryptoGbn = "", cryptoKind = "", length = 10, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String hiredate;

    @ElVoField(physicalName = "empno")
    public int getEmpno(){
        return empno;
    }

    @ElVoField(physicalName = "empno")
    public void setEmpno(int empno){
        this.empno = empno;
    }

    @ElVoField(physicalName = "ename")
    public String getEname(){
        String ret = this.ename;
        return ret;
    }

    @ElVoField(physicalName = "ename")
    public void setEname(String ename){
        this.ename = ename;
    }

    @ElVoField(physicalName = "job")
    public String getJob(){
        String ret = this.job;
        return ret;
    }

    @ElVoField(physicalName = "job")
    public void setJob(String job){
        this.job = job;
    }

    @ElVoField(physicalName = "mgr")
    public int getMgr(){
        return mgr;
    }

    @ElVoField(physicalName = "mgr")
    public void setMgr(int mgr){
        this.mgr = mgr;
    }

    @ElVoField(physicalName = "hiredate")
    public String getHiredate(){
        String ret = this.hiredate;
        return ret;
    }

    @ElVoField(physicalName = "hiredate")
    public void setHiredate(String hiredate){
        this.hiredate = hiredate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Test01UserHeader [");
        sb.append("empno").append("=").append(empno).append(",");
        sb.append("ename").append("=").append(ename).append(",");
        sb.append("job").append("=").append(job).append(",");
        sb.append("mgr").append("=").append(mgr).append(",");
        sb.append("hiredate").append("=").append(hiredate);
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
            out.write( com.inswave.elfw.util.TypeConversionUtil.intToBytes(this.empno , 4) );
            out.write( com.inswave.elfw.util.TypeConversionUtil.strToSpBytes(this.ename , 10, encode ) );
            out.write( com.inswave.elfw.util.TypeConversionUtil.strToSpBytes(this.job , 9, encode ) );
            out.write( com.inswave.elfw.util.TypeConversionUtil.intToBytes(this.mgr , 4) );
            out.write( com.inswave.elfw.util.TypeConversionUtil.strToSpBytes(this.hiredate , 10, encode ) );
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
             this.empno = com.inswave.elfw.util.TypeConversionUtil.bytesToInt( bytes, _offset, 4, encode );
             _offset += 4;
            this.ename = com.inswave.elfw.util.TypeConversionUtil.getTrimmedString( bytes, _offset, 10, encode );
             _offset += 10;
            this.job = com.inswave.elfw.util.TypeConversionUtil.getTrimmedString( bytes, _offset, 9, encode );
             _offset += 9;
             this.mgr = com.inswave.elfw.util.TypeConversionUtil.bytesToInt( bytes, _offset, 4, encode );
             _offset += 4;
            this.hiredate = com.inswave.elfw.util.TypeConversionUtil.getTrimmedString( bytes, _offset, 10, encode );
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
        return 37;
    }

    @Override
    public void _xStreamEnc() {
    }


    @Override
    public void _xStreamDec() {
    }


}
