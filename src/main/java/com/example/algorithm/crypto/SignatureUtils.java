//package com.example.algorithm.crypto;
//
//import org.apache.commons.codec.binary.Base64;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//import java.io.UnsupportedEncodingException;
//import java.nio.charset.StandardCharsets;
//import java.security.InvalidKeyException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Arrays;
//
///**
// * @author neo.zr
// * @since 2024/5/20
// *  MD5(Message Digest Algorithm)
// *
// *
// *
// */
//
//public class SignatureUtils {
//    final static String alog = "HmacSHA256";
//
//    /**
//     *a6pSztU5etJqsDWidxigdvu3hVtmtxhYhnJU3n7nN2Y%3D
//     */
//    public static void main(String[] args) throws Exception {
//        String stringToSign = "123";
//        String secret = "abc";
//        Mac hmac = Mac.getInstance(alog);
//        hmac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
//        byte[] signData = hmac.doFinal(stringToSign.getBytes("UTF-8"));
//        System.out.println(new String(Base64.encodeBase64(signData)));
//
//
//        String s = "pfjlepcmkocdgpfjkeimbphidpmfbjpocflclpocfjaichjkhgdoeogekdfikdgjgiidjhimpmboipnkojamoehlkdnnbnacfhlkndhgkfcbapkdjadppgpdkbglabfoalhegkboecgnlnhkogmkidlhhgajigkofojgpafbgkhahbeombcjfiaadigiploecmcaagnldnlcijikcpeokdfekdkdpipglieppfempellllllhbllemhhiokhkaigloloehoohoamlnhcckmncklanjajbgieagnnhimbhpanfjiofadoiajodebllckngfjeddfmijfonbokppllnbboggpdhamceogpbcdihglgjidnknpjnbfamffhcghlnenihjcllgpaknmamlhcccadfoocppbmjjlecjhjhocgdgpdcfkpkdkffffklhdebhldinkahnhkkapkekffejalfabnakbepgbbkijcbfcjifibkghpiliffjlpdlijidfmdebmebpnfjkhpmfgjecdccjekneepjldphgigeffiddeolcokfmolpogbmohndegebndopikafdifmcfimdjpdnkeicdbbhkedbgdhemijnklagoncfmngknfpfedffllabadgkhfhpkgpmnibhfkmkocccdmcjecaflbfkhhadgbmijbfeaeccnkjgkgjmagohajhdeccnoecbmkaamfocidiapdpimnkgmbneicilnmonjjmmfkaofnghopnfbcmcilkcaigmadkcbabfbgkpapjdamgjjdejoopkdpiedfalneegjnfcpdcjiaefebgbopcailbhbknifejepmdcedcmdhonclegcejhjbbldpahmielemlemmjjebdlefhpanpkpjfbjklnanaomplcbpkgokneifliofkbggoooaijcdcbpojiioigbdmkpimjneadmhdajhfmjgiepagecnnijhbkabgeanojfapddpmebmkljhobffihlfcmckanpbnabkpbnihkdofpgknanbghffejdeahbakcopdjbcfinaljncjclgfbiefiibibhoolddpoaeenejelhkcpdcfhlmfcgenhgddgcddifkgpoiohllelkabdomiojojckikjaejllmobhapcfjdalhkmnppcnopjbhcbcnlohdhjmaegifaagckgdbeccpmdckihpmpjeomhneaakdennpkilkgjkbgiogpkalkmnobgnmkkdcfkmgeinioameacclonahcnabegajhkmpdbjcdkofifcjffmgbkoknmeacpmjeoecddhpbglfpfldemocibnffhjpmcoiofdfongcibpdjbdempmgdlhmkefagkbppbkdginngjglgggmopjgpgoilkniljeckhohgekllgpjjfgajnplhbekemnkigklhhmfghcjpnilfgngihafobadbahbonghnfdeebbaelhepillmheocenamegclknngbechdhehlininlpdcekblbhnbhegpoemnkmogmgmnhanbmeaomhdbehnlhpdckkjepnhekfcobhmgejllhcccbejmigbdoodmgbmfeklpaocjnddoeblonbppljpodobkkhfkldjclfkhiaeookflpcbbkebjcngoooambkheghajjnfnkhghgkkbclbfpckmoigflmmhefljgimpgeafjmblljhaiklcmhdmbededahaclbmialfllijnllmoendlogmcpjacleefoepolhkedpfgklnnajlkffikifhdfellochbfnkppkjampapehloibaalhmhejagnoakgmnombhkllmlknfomnojgmippcefiehmdolobjmeeofhchoacajigdmndpofnjkpfoacpkileaedekipaippldlkabgnpplabfbkmilicojobgiginnhmacepkcdcddoejndeeajfgbcfnmemlmdffjbmdleikfgkghpbbpbbdomncefocnnalhkonhgjldnmnnlgoglnplaidemhdojeipjadofiijkhfjacioeacmhhdlocdgldocameikalckljihceolkaibiegakkkgpdkeakgjhdijkkkfpmmieebhdgjnifgnabnegaejjfplmdkmmcofnmkophaifcalgkamblfiaaebchdeoknpblcnannobjdbochoklghbfinpahjcjhicokollofplaggjkcbleomiigmfldejkngjengblmimfbhdmicnggeepejjdohcdbngmephldljignpnbeccgkkoeccmbipnchefmakpofimfhjkomfoikdidcjmimfheemhcgioffflhnibobhnafghbppedmglpiifmhfllngjibahgdfpooidpikaaimegllmaakhjlakeplggidmmaipnepegnfigedmkeofmoiiigcacloghgkhlacgejbjckpohdobipkhafcgecmkciomjngmmamdmphgpfmcfolbhgfapijnnjmeekocjofajagfcgffimddapmmchmkikmejogieplolokbekmkocmehnhiajfihopddcfjfaohfijhinmpfpdhibajeboniffbabidiblfdombkdihodokicbbbmmcbjlhgecmoacnldnbjifkiajeadioaiicjglaaaegdnaonoahamekljfhcjcbdmahdgbjeiidpmhgllehigegeeiiabkbdipciinghdhdhcahldgkgchcamommiphambclchfdfambfcoolepaoikljahbcomjnkdlihdanmhlkfgknjdkhpoelafhignajbmjdfdodhabbbieeealgdinpfcnjononfngojocflbcgoilpofnjhikgkjcjdojaenoboaicdfdifjbcbdkfamgkcmggllpmindgopkmkidcpbpnhpaopejbmbkphijdpkoegkgfiobenljpdodeggdknepackgknlhkfkalnfjajehokdkfdkhhenbgbjagiikgloangigopnoljpglllebahapknhjibagppiagmmfjpdfngnnbjeljgkehiacnldcahdamoofhbcmkhdbgeofajkkamknbcdflicfapjgnkfdgkjgbolngompbnaoofllilkiodbpomblnbjobbmnlgppdhloahgcfplpfaddpmlpabndkangianebbpcmkbeicooebmaddfaddndgephmkllimofbmmelgfnmpaplnllhaoepijghglejjelonlmfedgjknejlgblnmmaahkgdnoopmllijleilkphobmkabfkbcaibinicghailfabdlhffjmkomcbnmlbofflkpggflenfpkhikmkokjpmnchkoeejpcedlpcafoficjgncjfjbpgfmimocdjngjknfidkbmdgkclcfcooenflndabaobjacbddaffmepmdlflgakeapcflhchmekaljnkahopemjlidmkdnkgkdankhdpldcjabeecbmcmaadpffoagkgoeocfgannggkdkegkcjfeeegkihfkokpkdcgbkicdbndhbjcococlcmbnnehbackbjknfeghbnpjddnejgdemjfbojohgjjeebhnkohonlgmlopmoamifejjcjllnihkkhbodckfklianeplhhojdekdgegpjkocgkehhhcalkkmgmhpppllofjmmbeekjghimekjikgmgociieflohbgjpdjakbobbfihbejbnoichmldbiggcdkllejkgaifegffkhefmibdbennmgdijjgojnlmnboklehklceglbkgnckndiechmbhblcaegdcegiodjniahjknbjikkpiifhcamgpgfabmeppimebhbcbkbdppilepbnbnhgndncobkncmdbafdfjkbgdbpkoehmohgbiglepdoiepnjiacbhodjbbhpgipakbnjakbealmbbdaoajlofmlgbnmdpakmfamanechnbpollhhjfhnelbkocbfknehcaamflfkabkggjdkakkfihbnghjgpiemehgdceacdabplgdoifchalidhhnejlfjibodaphkoajpegkdjehpdfjnajlegchoogkidnfclnngfekhmdehjpgbbfjhdlfbgemjnekoccjfihpekjkjbkhdcofjdjiipbjppjmikecnncmpplobpegfaabfmmgaagdpckjnpjmmklnnekknlnikpagdgnhecpeioadmajnjgebffdboemcnkegooejohmnkbcclbfnmolmgcdamangppgjgaecdbmfaaciaaibjdogmeogfoodmgnpmaomlhhnenhdhoadacjgbalkjncbncommaiapbhmpgogjpffalijjcnbkieeagmojfcpedblkdiblanfppjfllbppngdonjjehgnjopohcoflelhpmgfpfakoljfalfbffjklilihhaaoknapbiabdffhhkhfalehodojhnjhbhfmhdomhndjoppdgkjlomcammogajjmmbihlablcjacmakbidjjchhehfpekfghdolbofbfiohnpeeogoajicjooohgmappbmjheomnkbdjclihkbgbmpgfajkfpdlmnbkpogbeamgpilpndjmeneoffhjnbbbghfggdobmldfjnelpcledlagbiohajaglafgdfdbgebilpanopljeedidfplplapdhjdhebbgaigonhaddckeeogggljmonmifdihokmdimcckjcnbphbopkldddllonohoeglfbijjgkpkhidegcahfmogbjfolickkmeffahjpknpahjjiihocebjpjaepabfpoiflekfkomlmmanjcknkmmmamnehhkaekdfcmmdanajgdacjjmocbcibdioanchdkdpebapjlmhaiefepmldgglonmjoknajokiapohcelobadlajiohhfbjcljjnjoebmgehkmklhgoampojlcdkhcmnlkfbiafhfnaealmnmbkomoopjimilldpminkdiladfdelkijfheokgnhmglinkjmdgnmahmpalhmkocmahcicanhjfhnmddagiehcjojplcongbijgndaphmlippkpghgjjmjkpmeceibibehneglbmiffaponnngnbeahfhdlmibaagkldoaanbbpphlikgnlkjdimfigjdmaiaomlehlgdbpgikogmkflpmlllkgjjigdikplemgaolooelbolcoldalofobdjmomajabmjmkeiheofiinffmmjhljdfkelgdgjljkkcdpcillgchdponldmlkpjdjakophimicpebkcbjeojliajglljhcbhanoijejpeehanhmafdjfnbdldepcbafeofmfnghcpkefpnfacdibcnobhpkgbdjilimbifjbknhdonchhhdaajdjdlmfkliamigplggaphjefihoghoadoclgmmfjjhkagjibbgeddnnjjdjppigkahohnbojjfpmdheboifhmjpbcebeeolclbggaoipppmnjkmndohhmljmejelpdbmdchilmoekoikmmidopecfmnelalemnagakklhpdiaebdeiciopkobdmbajgapcedhacekhadcgipfdhiiaeiamagnnkljhmedjbjdapgfmlabkkfijejhajnjnahefiieogliofbppbbfbnklcnflebiiejpnfimhplpceglmfdgpnjelekifedoflmhikbegceinndbepocihbkpjobimicmdeenlbedkhhgeddlpllhhpdbdbccfefofcmloaddgnpbiamdmgobdpdopippcnpjeopnppefakpacelcfbbhkkpimhanfldghbbdlmlmfdibicjmpceejiiinokbmlhboamhmdhdbffaekcifdcodfcdaolephimgelijjmjhilfgpnmicajhimddefkeheknmmkggjjbaobgpiekohacakgkakicjlhiaegamjbjdhhoadmhfgjkjanhfofnffecggkjbnolhlahbmplkmecoefgepndffclbaomdihlgnamcalhenjahaebllfligbcjapjejcdhbifgcodeflmlebmpjlpjhcdifonkjnmfmamhmmajokcbcdnnbdmaclebpmdngndpohonfkipibnipnjjlkejipmkomokjmomlnbofpnoaogajechfaiemkklaefjplbjhjjpdimobkgcgcjgnfopmhblfjiaanhmbodhpgnmgjfgfdcdapnjcebmlfmfjkimmlifokgmfknoiffecnhdnlppfpbjpmjcmedjmmcnbpicihpmhbdamlnpakmfbdocoidokaooahldnnpmmhdalklhnlbodmhihgohgeoihdikamkopmikaaiapaighbpblpjoleknnfgcimfonknahapnmfgjhjlcnkobglkokaacnlljgfamifoaddilkbkmjcnllnjeojklkkniiefpgjlhameemdibnonhjleoeiaccejgeaiebcnhapdbgefjgmpenjcmhhljkaiipmhfkajaagkahmjljljckkllencahbibgniogkibmkbmfpendjfndgehanoclmeepejpikaghilmbfnkkpjdmaikpbaiacflonbcjbkehcbbmjnipdjkgahofmkhkaoobikcfehohedhbopadocopkdodmiogmfemebjocddlmhdmikibkemfhdiholbgonhbggmgonlocpphjihdjnakbdmdjhhgofilfpnbnidmboccfhelikmmfmcemnincpfclnlifahhidfpbolhggihnemgengfkbagbjibabpfmfdncihfhkefadojddhbbojgkhpoeaneabmhncgokjfnhhbdecnhabembijmndeiflnbgcfhfnmjmmbplcfejcgcgdphfkggogbchbpdnjlhokgphiolkdckalfdkkoipfbhonnboadihmfjlklmlplkplbajfmjbodbjdgenobbhjcadcdokmcfhengcpldmiifedfhjgaaaeakhmjpmddclpkiepefocfikbchhelnhiceiapokengebadnknlbmegnfnmhfljadfcndiidbbndjeejoolebijcbacehlplmbhmncipfgmimlcmlodibiobiodncpbhmpkccmncmknkbcpgihnkfagfhiphfckndjgfdfinhjkfnobahpdjbbgcjjeaaikdnncipcchmffejilonhoeimmelobjlnoecefopopgcgbgpamkkkgmaoeiddlbdlcjogomlepmeapghfefielcpdijofifbncmkboojhncfigffdmmedfbmhfaiimmkfjhalmhmbjfmcmkbkgjddmkeomkmjmbdkfnafppjicbeifoimmpcfpockpnbglaobfcccfnfneajcfjgjeonhpbffhgfmpdghajjeimdpbppiibiknblkihgjfkjbacndmdbijjjapdfibhimbbjccnhkilfllpnbbfmcegoomhmhhefafcjnenkfhjdedlajdcbjmhineoakdenlaibdbkncffndeoalnfloiobbkpplfnkhdgappflcgplbpmjopnapmmnoohccpkjlbchmeikpgokljdimkijapgjbmmmdkejjgeemdlhlpmceghahgdffcdkagngndekejfikjlmjdghnlgcchnhoekoagfmlppoagnhcfcbbofpcmlpecncdimkiplfcnejkjdnnndoffkgdejlecpofeigpbbijohcghjbcpnnkbkpmfnojdndaajbkcedhfhifibncakncpkekbidjeclhdjnfljimddogdiobefddgpoebohmjlnbcegmchainadokddjbkooilnpkmocgmfmhegncfcknheifieipbbpfgapehpmkhdohajgocipjibhinkclghddkaaoonahkjpcmgkbmjglnbpidelneidlcakodjbbbpjbbodocnadfmepihldfmnknnnkeneodpnpghjkgkjhdbhdgkeclbchmiaphbbjmmngpoebpcplmedemomiepkinnfkjcgbfbdiopejflbcapmnmjodohaegojfifhkbdjejfhmhhgbkekgponjnkeliplpcimnnojebgbjnnhcmbcdmpkibifgjdlagennjibmpocclnpldmhbiknijhojikgipcheldjfbjodlfmbmnbhkomclhldmajkhknocnjbnecddbkokhkddammjbfpfhcjdeekbjffonehpbljopeibfilifofeeaidafecbdbmjenipiijijkccpcaklooapojcpbnehdmbhciecigphjlclbglmhgkeejjpcbnmoemgadmdgbahpjpmcbnmkcaoblopfoeefimbhgndelaglilkcmanplbhjhjlinfcondlonfehkcijmhchgbimofoilhmdflnceocdlhpbgooldpngfpgcnhidbokdjhgjajgicdcgnbphbagolpgpfplbgoomgaboaphplkfeklpenbnicpjieimkpgahhnfeoboameinajhnckamcabhpfdigfchfcicmcghcilncdnnkfmkkfgmampkiananpgnphplajhhifkjoicdmgfeoclodhpdniigblaleigbkekjjbolfckdepbdpanphnmecmdciecdanbehccmmcnpbpmpgddpndhpkfajpcdbpckabcejncehddmganomejpibpdkkenfegpeopoflmlfigjaeggdodciccchcekgbceneonpolbfdlpggemlchinifkiapjnciojfiohaahegfbhjhmgjhgdmgigbgjjkjlbakkmkhkjmppajpphhnlnggacgjbmpgffnnahchjgfldkpadecpggjjcpoiklkniponjigpjaoopainpnjnihmknkmeigdlpdhbjabafimenenkmijbjhlcnbglgonnkphlfhiopkbagmdglbhabifenifgkcelfdifniggmnmphphpailgpcajjpicjjlebdombjpgkmjjhjbbbpgcnhbapkibommcmhfllibcdegidhencajpjkpdcplenijjfjjhhcplfgblciclicagfjlmdgifbggafaiingnhgmbneegnlcgdhjdpogbfhdohbmkmhbgmnfphcgfjfdfkigbalkggbgmjjggfloekmddcpdmnaaloamcllcodadlidnkjbohcakojmpekfmbcdeehbdcnjkjapmimgjeodpkookcmcliaknmoegglcmpfkdjijhjfifendegbjjdfapenhbakdnoffohaklnhhdnbbjepipcbomhknppehokbapmoednphdolbhkblfoiiimlohanppfpeobodjdfapblcdgefcaiapgpbaaadiimpejldpglanlo";
//        System.out.println(AESUtil.decrypt(s, "connect100009022", "100009022connect"));
//
//        String data = "abcd";
//
//        String c1 = md5foo(data);
//        String c2 = md5V1(data);
//        String c3 = md5V2(data);
//
//
//        System.out.println(c1);
//        System.out.println(c2);
//        System.out.println(c3);
//        System.out.println(c3.length());
//
//
//        for(byte i =-100 ; i < 100; ++i){
//            System.out.println(i + " = " + String.format("%02x", i));
//        }
//    }
//
//    public static String md5foo(String data) throws NoSuchAlgorithmException {
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        byte[] theMD5digest = md.digest(data.getBytes(StandardCharsets.UTF_8));
//        System.out.println(Arrays.toString(theMD5digest));
//        return new String(Base64.encodeBase64(theMD5digest));
//    }
//
//    public static String md5V1(String data) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            byte[] md5 = md.digest(data.getBytes(StandardCharsets.UTF_8));
//
//            System.out.println(Arrays.toString(md5));
//            // 将处理后的字节转成 16 进制，得到最终 32 个字符
//            StringBuilder sb = new StringBuilder();
//            for (byte b : md5) {
//                sb.append(String.format("%02x", b));
//            }
//            return sb.toString();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//    /**
//     * md5加密
//     *
//     * @return
//     */
//    public static String md5V2(String str) {
//        if (str == null) {
//            return null;
//        }
//        MessageDigest messageDigest = null;
//        try {
//            messageDigest = MessageDigest.getInstance("MD5");
//            messageDigest.reset();
//            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
//        } catch (NoSuchAlgorithmException e) {
//            return str;
//        }
//        byte[] byteArray = messageDigest.digest();
//        byteArray = new byte[]{1, 2, 3};
//        StringBuffer md5StrBuff = new StringBuffer();
//        for (int i = 0; i < byteArray.length; i++) {
//            String hex = Integer.toHexString(0xFF & byteArray[i]);
//            if (hex.length() == 1)
//                md5StrBuff.append("0").append(hex);
//            else
//                md5StrBuff.append(hex);
//        }
//        return md5StrBuff.toString();
//    }
//}
