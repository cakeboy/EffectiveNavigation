
package com.example.android.effectivenavigation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import android.R.integer;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;

public class TelecomInfo {

    /*
     * private String mId; private String mCorporation; private String mPayment;
     * private String mOutraNework; private String mIntraNework; private String
     * mLocalCall;
     */
    private static final String mCht = "CHTtelecom";
    private static final String mTWM = "TWMtelecom";
    private static final String mFET = "FETtelecom";
    private static final String mLocal = "LocalPhone";

    private static final int mIndexofId = 0;
    private static final int mIndexofCorporation = 1;
    private static final int mIndexofPayment = 2;
    private static final int mIndexofOutraNework = 4;
    private static final int mIndexofIntraNework = 3;
    private static final int mIndexofLocalCall = 5;

    private static final String[] Cht_Phone_NUM = {
            "0987", "0956", "0939", "0937"
    };
    private static final String[] TWM_Phone_NUM = {
            "0918", "0920", "0922", "0935"
    };
    private static final String[] FET_Phone_NUM = {
            "0916", "0917", "0926", "0930"
    };
    private static final String[] Local_Phone_NUM = {
            "02", "03", "04", "07", "08"
    };

    // private List<List<String>> mTelecomInfosList = new
    // ArrayList<List<String>>();
    private List<Payment> mTelecomInfosList = new ArrayList<Payment>();
    private Map<Integer, Payment> mtelecomPaymentMap = new HashMap<Integer, Payment>();
    private Map<String, String> mTelecomPhoneNumMap = new HashMap<String, String>();

    // private static int TELECOM_LIST_COUNT = 0;
    public TelecomInfo(Context context) {
        // Resources res = context.getResources();
        // mtelecomPaymentMap = new HashMap<Integer, Payment>();

        // TelecomInfo telecomInfo= new TelecomInfo(context);
        int TELECOM_LIST_COUNT = 0;
        try {
            // Resources resources =
            XmlResourceParser xrp = context.getResources().getXml(R.xml.telecom_list);
            int eventType = xrp.getEventType();

            while (eventType != XmlResourceParser.END_DOCUMENT) {

                if (eventType == XmlResourceParser.START_TAG && xrp.getName().equals("fee_project")) {
                    // String tagName= xrp.getName();
                    // Log.d("abc","tagName="+tagName);
                    /*
                     * mId = xrp.getAttributeValue(mIndexofId); mCorporation =
                     * xrp.getAttributeValue(mIndexofCorporation); mPayment =
                     * xrp.getAttributeValue(mIndexofPayment); mIntraNework =
                     * xrp.getAttributeValue(mIndexofIntraNework); mOutraNework
                     * = xrp.getAttributeValue(mIndexofOutraNework); mLocalCall
                     * = xrp.getAttributeValue(mIndexofLocalCall);
                     */

                    Payment payment = new Payment(xrp.getAttributeValue(mIndexofId),
                            xrp.getAttributeValue(mIndexofCorporation),
                            xrp.getAttributeValue(mIndexofPayment),
                            xrp.getAttributeValue(mIndexofIntraNework),
                            xrp.getAttributeValue(mIndexofOutraNework),
                            xrp.getAttributeValue(mIndexofLocalCall));
                    /*
                     * List<String> mList = new ArrayList<String>();
                     * mList.add(mId); mList.add(mCorporation);
                     * mList.add(mPayment); mList.add(mIntraNework);
                     * mList.add(mOutraNework); mList.add(mLocalCall);
                     * mTelecomInfosList.add(mList);
                     */
                    // mTelecomInfosList.add(payment);
                    mtelecomPaymentMap.put(TELECOM_LIST_COUNT, payment);
                    TELECOM_LIST_COUNT++;
                    // Log.d("abc","xrp.getColumnNumber() =" +
                    // xrp.getLineNumber());

                }
                eventType = xrp.next();
            }

        } catch (XmlPullParserException e) {

            // TODO: handle exception
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for(int i=0; i<Cht_Phone_NUM.length; i++)
            mTelecomPhoneNumMap.put(Cht_Phone_NUM[i], mCht);
        for(int i=0; i<TWM_Phone_NUM.length; i++)
            mTelecomPhoneNumMap.put(TWM_Phone_NUM[i], mTWM);
        for(int i=0; i<FET_Phone_NUM.length; i++)
            mTelecomPhoneNumMap.put(FET_Phone_NUM[i], mFET);
        for(int i=0; i<Local_Phone_NUM.length; i++)
            mTelecomPhoneNumMap.put(Local_Phone_NUM[i], mLocal);

    }

    public class Payment {
        private String mId;
        private String mCorporation;
        private String mPayment;
        private String mOutraNework;
        private String mIntraNework;
        private String mLocalCall;

        /*
         * private static final int mIndexofId = 0; private static final int
         * mIndexofCorporation = 1; private static final int mIndexofPayment =
         * 2; private static final int mIndexofOutraNework = 3; private static
         * final int mIndexofIntraNework = 4; private static final int
         * mIndexofLocalCall = 5;
         */
        // private List<List<String>> mTelecomInfosList = new
        // ArrayList<List<String>>();
        // private Map<integer, String[]> mtelecomPaymentMap;

        public Payment(String id, String corporation, String payment, String intraNework,
                String outraNework, String localcall) {
            mId = id;
            mCorporation = corporation;
            mPayment = payment;
            mIntraNework = intraNework;
            mOutraNework = outraNework;
            mLocalCall = localcall;

        }

    }

    public String getId(int indexId) {
        return mtelecomPaymentMap.get(indexId).mId;
    }

    public String getCorporation(int indexId) {
        return mtelecomPaymentMap.get(indexId).mCorporation;
    }

    public String getPayment(int indexId) {
        return mtelecomPaymentMap.get(indexId).mPayment;
    }

    public String getIntraNework(int indexId) {
        return mtelecomPaymentMap.get(indexId).mIntraNework;
    }

    public String getOutraNework(int indexId) {
        return mtelecomPaymentMap.get(indexId).mOutraNework;
    }

    public String getLocalCall(int indexId) {
        return mtelecomPaymentMap.get(indexId).mLocalCall;
    }

    public int getTelecomInfoMapCount() {
        return mtelecomPaymentMap.size();
    }

    public String getInOutLocalNumber(String number) {
        return mTelecomPhoneNumMap.get(number);

    }
}
