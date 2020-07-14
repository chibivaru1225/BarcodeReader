package ZOA.Android.BarcodeReader;

public class Util {
    public static String BaseURL = "http://webhn.local.zoa.co.jp:60001/magic94Scripts/mgrqispi94.dll";

    //public static String BarcodeURL = "http://webhn.zoa.local/Magic94Scripts/MGRQISPI94.dll?APPNAME=WEBHNCTL&PRGNAME=itemSearchByJAN02&ARGUMENTS=-N";
    public static String BarcodeURL = BaseURL + "?APPNAME=WEBHNCTL&PRGNAME=itemSearchByJAN02&ARGUMENTS=-N";

    //public static String ShohinNoURL_A = "http://webhn.zoa.local/Magic94Scripts/MGRQISPI94.dll?APPNAME=WEBHNCTL&PRGNAME=%8F%A4%95i%8C%9F%8D%F5%BB%CC%DE&ARGUMENTS=tenpo%2CshohinNo%2CshohinJan%2CshohinCode&shohinNo=";
    //public static String ShohinNoURL_B = "&tenpo=&shohinJan=&shohinCode=&kensaku=";
    //public static String ShohinNoURL = "http://webhn.zoa.local/Magic94Scripts/MGRQISPI94.dll?APPNAME=WEBHNCTL&PRGNAME=itemSearchByNum&ARGUMENTS=-N";
    public static String ShohinNoURL = BaseURL + "?APPNAME=WEBHNCTL&PRGNAME=itemSearchByNum&ARGUMENTS=-N";

    //public static String ShohinCodeURL_A = "http://webhn.zoa.local/Magic94Scripts/MGRQISPI94.dll?APPNAME=WEBHNCTL&PRGNAME=%8F%A4%95i%8C%9F%8D%F5%BB%CC%DE&ARGUMENTS=tenpo%2CshohinNo%2CshohinJan%2CshohinCode&shohinNo=&tenpo=&shohinJan=&shohinCode=";
    //public static String ShohinCodeURL_B = "&kensaku=";
    //public static String ShohinCodeURL = "http://webhn.zoa.local/Magic94Scripts/MGRQISPI94.dll?APPNAME=WEBHNCTL&PRGNAME=itemSearchByCode&ARGUMENTS=-A";
    public static String ShohinCodeURL = BaseURL + "?APPNAME=WEBHNCTL&PRGNAME=itemSearchByCode&ARGUMENTS=-A";
}
