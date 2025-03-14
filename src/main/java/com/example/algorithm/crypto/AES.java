package com.example.algorithm.crypto;

import com.lazada.lazop.util.Constants;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Advanced Encryption Standard
 */
public class AES {
    public static final String CIPHER_ALGORITHM = "AES";
    public static final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS5Padding";

    protected Cipher cipher;
    protected SecretKeySpec skeySpec;
    protected IvParameterSpec iv;

    public static void main(String[] args) {
        String s = "fabadkkclfmhlncbeflnambogbbcaaepejopmionjghlkcpppgmjlfbkpefdkffbjpcigdjmffglbamjfjljglbndhmifddhlffhcgnddonccjecnbkoceidljpkbjglbjhcecodpgkjkcogaoaillbphiifhdbcnngfimfkpcgegbbbelijfmhfknphgjjnmfneelhmlbijndfgakkjlnjodoambbfgcmpamagohebedoealbkminllcnpohoklmedakcjgjklnhmnapikakdjmhejhhfaeackaefkifmkgnmgleglelhmcgjcnobomggknbkebjillkkacgofgnkahdmbblccahddahphmcgdpcifgkfnepmplooalofkkhbklhnaddcnkghhcmhiocokbeciphmipokagdndamnhcecbmbfbenjnbkfpfipciichpmnpdcjlhfneapbjhlfgmiiaehiimmenppaidgjhikocnapleekphgmebocohlcnmmiajamfmbceboojfagllbfokjbhfdmdbdlcpopnnfcobmfdmdphgklgpndpfnaogjkpgpjajainflnbfmcgkifinejjaphcgbpdagdbgpapkcdchigdbhkhbbipaggimoacihefakbmgimknbpanjcdfapnllbffbmgmcgiklompamahkbkemhidkkbjmfpiikjjfbiponlfamfinpljjcjpjkeehjajedokmocffnjdebahpiohbmdpbglheegdambhgbmgogefmpjmambfpngmmeiajkalfencdfhhbhbodkafpmckpnondaemenihdoodejdgnbpkllcjpblepmkgndakmchbdclaeecnidfndokfknkcpokedocegkapicdlpbofhbdjanbbfloadnfbpplkcmelaifkdalfcpbjejkmoakipfjinmnkpngbheodbgaanandkdnjillpdjoflfbhaleccbafdninahbfjbejdkpjanccladngghkejdjfcnjgllemnlfhobfkiljdfnpfobcpoankmnfehmokepknnneamijdjiekadldjoemcopaafonpkmbhmaainmjmgdjoeomlkcfcjffpjidkogikkjildjhpdpcendhihkdppfeebgjegjfahlhlghgclodeeakljbmlnhllmpaipanhpmbngaklinehdjclionccnobiafmbenaejcpnlbgelfdblijfhcfjbmnpfehfdjefaccjnjkgoaafljdbiecghgnpcejageokdoknhgmifihbpgdhknkmjngofkfdmeekagggmjfkhmacddlbkdbhdmhbffpkploefimbkphaeaifmeblgdmcifojgcgkfjpoacngjdhdoebmobchaibanifdhklioenjjmiegaipdjejonikfmnoodgglpbagjgakbanfffogllcinbcopilfplcffnhcgibdpefjmmdndgmnajhaoogmbakjkflfdfkdkiidiopnlgggilalkhfciahcgmckffhbigogjddbklegmljhdnjfkpofcdljaencfanhjldmnnkgciepcegmgjiddklnialpcblfaileebecjhdnlodlbgeamajggjjempgmefofilfehaejmakoknbbplpcllefnpdncpajnlldbhbjonicobdkmnmmaiimhpieobkiebammfckmfebbpglbilbckbcpdipdjnonkigpgeiglicoackpfjmachehonplnjjbpjpnginnbeooedhfojafijhajmlpnfnleggehkefplciggjieikkdggcmjpmgjmhjfnoknpdalnabjkfbniodpmjkppdpoclgajmifhaffeniblnofjmcglopjjklbgboallcmoccphhgjagickphofjajnfokaleagdenmkopbelgpgblepealkbiecidhmhdalippdeadfbicofihpbmigbfbaldbccdnibbjliibaomnlkamongbdbagcajgnjejfhgkkohhdmpbabmgfifokhagplkjdioieagdpmebclmcnnfblcekgdjhfppibjjdmiclcegjpkaglghcgihfmeiodohgeaejhenglfkebhilbmlkiimpeehhmblbigieammloepanbfdnhclbhdeoaebcillebioofofllighlgcfccfhhajhmmmgmaagaeffoeihmldfkmidfgpbelhaojnhjlmdkpbbfmgepmencnnhdcikladnbgllfmloiieiafioemlbikodmknkfflelkbfoejppjcjjgjliifdlpcbpndbnanknoigidjgmclhjjahcnhaempnhfdnflaikcdekfldconnfkckcncokenbfllalpjfiaehelnmidhfjiinocfojhkkbenafklkjananebpajghmehakebecnocflkgkekoajicibpdgddmlggmggbigpjfkkpikmgkhbhmekdjkadelhdmcllagmgadeegfpoadilpmneigbobmdgfllclcmnhdginfodbaacpcokjfjdepkbomgenpfcbdgcfjamkeaeabfnknoebaojkejongoiifgmcalnijljacbicbicflbioegibdbojboeinfnfblfcnkfjeglnnbfdmaagfcngblompohmahalmedhjfekohjillmggomebcafplliemfeochafmmdijekmbeeokammhepmeffenhljmeedlgepojnglmolnaljlmpgbdmkfnpbdnmkcgklkpkiapgfjfadhokabipdibcneglbnibbepejddlggejodpacbcahbfdkhjcpfgobiiekoielmpcpageaaepgbdpdjdmbnokkbjdmpbfkkgafgobjfiibbbpoeebbdhnokjegbhgbhoeeopffbnnflfefbjkapnipkgclhbfflahaelkdphniceabhekoimcpoddeonanfpobjogenhkgdnifokfflbnldacmfkhdofilkdhgoedpadfmfhlamldjmdnhdjbnlekjnegjbhdgenfncbbpjkijkejodmgmiogmmkenocoeehpbiemilikigjmobphcbddjfogfealpdknbjbeejfonheakabaaamhmelnoiinfepfkpbhaaodkigalgeloljoojdjpgkeipbkkcgmcjibjmknogkfedmoboflamgeifkimlckfodllfbdpckfnpepjkfcdoaeliejfcdfibicfhpcjpmkkgkggjlilmfoofgkaidmpnmhnkdomibepjanbdhachippanffnfcodgiocfjikbodkclhhfgnelpheggnpohjdpjeakgidaddgkegnpkjgklnmoeneggjfdlellkmekiijcoidbaocpieennkjfkaddapmppfcmgnlbmhhknbjcgleipiendehcfipmkmmdhkldppdjcdpihfidbeliiigncbilabchdndilodkelcffjjlgldoajodmnjcimkehoadbgdoffkmdbfeelagdadjcjmplfafnehclmnifmgemicmeajcnbcanagfkcgapglamejnbilnonamnjlpfnkabklodcliffcajokdgkjjbbiebijhganeabibadphaecligfofibbmabgbgmipehjifhnkdnbaaahggnllllonmdbmkohjmacjcbdohekfammfkhlpphligphcllccbkhiidplcbcfpicabgfelckkknppkmbojhjnnnilnbianjdopfapfojgbmmodgdndipceijhddelhcfcemkociihbgkmjacmjbagchiingphlaneoojmhhlihbnooeibbgpibjjmdipljajmbanepdhklpcblohfbjdedknebkkaadmakgejmeonkfkmbpfpnfhgclbdbekolpiljnpoemnolfembhbomnafmefjgldagffhnkimkccemkifbfogimihnkeekkcjnaoodbbdbcdogkjdeekpnejhjoadbeibmbbhmlhpjbngnphellmagihngehhgplecegajamhgfhanfpdacajjchojmnlfipaglhnaclkdcliognlnogllhopkpjkbiamcloibnmlmeeaghopikdhpcopacjkakkjgbhhfkjnkhgecdjgkohlbcmfogdfjjpmknbelfjhpmcddnobehginanjmdmaklmgiabcoiakpdaddobieaahjnabjoeafnhgnihpkhkojljaclgmfckggpibahnblddpfbkccaflppamdoiiecdamofpnllaidcbipdifeenmojhhjbfllhnhelhompkcfnhmpfgihoiphomlmplahbdjkbjpipdpejjndfjgflfjmhjaekbinbaiefdichcikngkhkodepechnihcnlcjgifbnjdplofpiokcjegboagomaloakpkflenpknoakpfompjbpkooabebngfhnfnmhhdknphnpdiphabpbncgikmdfgjgfbnepgbjajbmdeleeggjbjlhhaclopicoalbgdmpfilmckhhdlgpdlmnfbddaoebfpdgbjjgnblhiapinlllfimnopjomafnabcnfmjefnckgebmfmbhdmnmlbemmgdhgpiaeahbhnnkdnmopmnljhldiepopfpfnapgdhcaccknloehaehibdbeobaioacicpmfkjlielmcamdoalmelljaljnapgemamabfnolggobhnjoeckdmknhbpgiaahcodbkmnlhndhilbhgjncijkfoipdmifmjcchjcjlpdjnnpmijfknaihgeklhogoehnialmcekmcjbolojemmbgopcmkjabdodmcpdfljeekcflilaailjdnmcgimnifdlghkllgaoppmmoaeeogmjcbadjpiffciaklilkggakaheakdlkgflpbelaapglhhemnganfffdicpbemieefdhcjpddifhpiacjeeajpgmnhpdfhfklieonmiephgihaciogefnmpdchlplficipoejepmahbkepelgbnemgggjmnmmcjgcchdhkejgfjgdaceeojcpnemhpibdbekcnagfmlkjkgjpjadgiebfonbpfnpclbkencodbikhgnpgkloiobgpgdfhhfkgkehbapgcemdljiaanhhkjikolcojklemjdbanajcbmmopgdeeaonecmhkmfplbpgnkpigdbmldopafknhapgnigbheghmfgnjllagmeohoflcblpjdhlihnbgdighofgibimnlidafdfhbgdnmjndjglgogfnlgefekjnfojcmoikbjflkhacnidhablelnpifbmlabdjhlbiognhnkjkndfgnjnedopekoeimeiiigpdcimcannmhdfllhnmlopbbjncegdgpenemnmnpeealefofhcjoioighgikifajpnconlhoiledfdiddebldgfckeaecfhcckjplmmngbhmlgcojeilmngpfnganmfkidhoaejbgmkcokllngbfepccnclhgdbikaddogfeimmabahiipakiobajhliafofpbdidcjbefmggmblegghcogncjenibpekimfglapfemnfbkggfjonlfdphpeelefkpjejjfjhhdoncglhmkgcoakmfhgonlehgihngihocjclpapkheiiflamokaghdcmoapdbmbaiffpfpepjknmgninmjegnehlljbmflaicbb";
        AES aes = new AES("connect100009022", "100009022connect");

        System.out.println(aes.decrypt(s));
    }

    public AES(String key, String vector) {
        init(key, vector);
    }


    protected void init(String key, String vector) {
        try {
            byte[] raw = key.getBytes(Constants.CHARSET_UTF8);
            this.skeySpec = new SecretKeySpec(raw, CIPHER_ALGORITHM);
            this.cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
            this.iv = new IvParameterSpec(vector.getBytes(Constants.CHARSET_UTF8));
        } catch (Exception e) {
            throw new RuntimeException(String.format("AES initialize failure, secretKey=%s, vector=%s, errorMessage=%s", key, vector, e.getMessage()), e);
        }

    }

    /**
     * Encrypt plain text into cipher text
     * @param plainText
     * @return
     */
    public String encrypt(String plainText) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] bytes = this.cipher.doFinal(plainText.getBytes(Constants.CHARSET_UTF8));
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                result.append((char) (((bytes[i] >> 4) & 0xF) + ((int) 'a')));
                result.append((char) (((bytes[i]) & 0xF) + ((int) 'a')));
            }
            return result.toString();
        } catch (Exception e) {
            throw new RuntimeException(String.format("AES encryption failure, plainText=%s. errorMessage=%s", plainText, e.getMessage()), e);
        }
    }

    /**
     * Convert cipher back to plain text
     * @param cipherText
     * @return
     */
    public String decrypt(String cipherText) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] bytes = new byte[cipherText.length() / 2];
            for (int i = 0; i < cipherText.length(); i += 2) {
                char c = cipherText.charAt(i);
                bytes[i / 2] = (byte) ((c - 'a') << 4);
                c = cipherText.charAt(i + 1);
                bytes[i / 2] += (c - 'a');
            }

            return new String(cipher.doFinal(bytes), Constants.CHARSET_UTF8);
        } catch (Exception e) {
            throw new RuntimeException(String.format("AES decryption failure, cipherText=%s. errorMessage=%s", cipherText, e.getMessage()), e);
        }
    }

}
