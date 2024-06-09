/*     */ package com.pnuema.java.barcode.symbologies;
/*     */ 
/*     */ import com.pnuema.java.barcode.BarcodeCommon;
/*     */ import com.pnuema.java.barcode.IBarcode;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ 
/*     */ public class EAN13
/*     */   extends BarcodeCommon
/*     */   implements IBarcode
/*     */ {
/*  12 */   private final String[] EAN_CodeA = new String[] { "0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011", "0110111", "0001011" };
/*  13 */   private final String[] EAN_CodeB = new String[] { "0100111", "0110011", "0011011", "0100001", "0011101", "0111001", "0000101", "0010001", "0001001", "0010111" };
/*  14 */   private final String[] EAN_CodeC = new String[] { "1110010", "1100110", "1101100", "1000010", "1011100", "1001110", "1010000", "1000100", "1001000", "1110100" };
/*  15 */   private final String[] EAN_Pattern = new String[] { "aaaaaa", "aababb", "aabbab", "aabbba", "abaabb", "abbaab", "abbbaa", "ababab", "ababba", "abbaba" };
/*  16 */   private final TreeMap<Integer, String> countryCodes = new TreeMap<>();
/*     */   
/*     */   public EAN13(String input) {
/*  19 */     setRawData(input);
/*  20 */     calculateCheckDigit();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String encodeEAN13() {
/*  30 */     if (getRawData().length() < 12 || getRawData().length() > 13) {
/*  31 */       error("EEAN13-1: Data length invalid. (Length must be 12 or 13)");
/*     */     }
/*     */ 
/*     */     
/*  35 */     if (!checkNumericOnly(getRawData())) {
/*  36 */       error("EEAN13-2: Numeric Data Only");
/*     */     }
/*     */     
/*  39 */     String patterncode = this.EAN_Pattern[Integer.parseInt(String.valueOf(getRawData().toCharArray()[0]))];
/*  40 */     StringBuilder result = new StringBuilder("101");
/*     */ 
/*     */     
/*  43 */     int pos = 0;
/*  44 */     while (pos < 6) {
/*  45 */       if (patterncode.toCharArray()[pos] == 'a') {
/*  46 */         result.append(this.EAN_CodeA[Integer.parseInt(String.valueOf(getRawData().toCharArray()[pos + 1]))]);
/*  47 */       } else if (patterncode.toCharArray()[pos] == 'b') {
/*  48 */         result.append(this.EAN_CodeB[Integer.parseInt(String.valueOf(getRawData().toCharArray()[pos + 1]))]);
/*     */       } 
/*  50 */       pos++;
/*     */     } 
/*     */ 
/*     */     
/*  54 */     result.append("01010");
/*     */ 
/*     */     
/*  57 */     pos = 1;
/*  58 */     while (pos <= 5) {
/*  59 */       result.append(this.EAN_CodeC[Integer.parseInt(String.valueOf(getRawData().toCharArray()[pos++ + 6]))]);
/*     */     }
/*     */ 
/*     */     
/*  63 */     int cs = Integer.parseInt(String.valueOf(getRawData().toCharArray()[getRawData().length() - 1]));
/*     */ 
/*     */     
/*  66 */     result.append(this.EAN_CodeC[cs]);
/*     */ 
/*     */     
/*  69 */     result.append("101");
/*     */ 
/*     */     
/*  72 */     initCountryCodes();
/*  73 */     String countryAssigningManufacturerCode = "N/A";
/*  74 */     int twodigitCode = Integer.parseInt(getRawData().substring(0, 2));
/*  75 */     int threedigitCode = Integer.parseInt(getRawData().substring(0, 3));
/*     */     try {
/*  77 */       countryAssigningManufacturerCode = this.countryCodes.get(Integer.valueOf(threedigitCode));
/*  78 */     } catch (Exception ex) {
/*     */       try {
/*  80 */         countryAssigningManufacturerCode = this.countryCodes.get(Integer.valueOf(twodigitCode));
/*  81 */       } catch (Exception exInner) {
/*  82 */         error("EEAN13-3: Country assigning manufacturer code not found.");
/*     */       } 
/*     */     } finally {
/*  85 */       this.countryCodes.clear();
/*     */     } 
/*     */     
/*  88 */     return result.toString();
/*     */   }
/*     */   
/*     */   private void createCountryCodeRange(int startingNumber, int endingNumber, String countryDescription) {
/*  92 */     for (int i = startingNumber; i <= endingNumber; i++) {
/*  93 */       this.countryCodes.put(Integer.valueOf(i), countryDescription);
/*     */     }
/*     */   }
/*     */   
/*     */   private void initCountryCodes() {
/*  98 */     this.countryCodes.clear();
/*     */ 
/*     */     
/* 101 */     createCountryCodeRange(0, 19, "US / CANADA");
/* 102 */     createCountryCodeRange(20, 29, "IN STORE");
/* 103 */     createCountryCodeRange(30, 39, "US DRUGS");
/* 104 */     createCountryCodeRange(40, 49, "Used to issue restricted circulation numbers within a geographic region (MO defined)");
/* 105 */     createCountryCodeRange(50, 59, "GS1 US reserved for future use");
/* 106 */     createCountryCodeRange(60, 99, "US / CANADA");
/* 107 */     createCountryCodeRange(100, 139, "UNITED STATES");
/* 108 */     createCountryCodeRange(200, 299, "Used to issue GS1 restricted circulation number within a geographic region (MO defined)");
/* 109 */     createCountryCodeRange(300, 379, "FRANCE AND MONACO");
/*     */     
/* 111 */     createCountryCodeRange(380, 380, "BULGARIA");
/* 112 */     createCountryCodeRange(383, 383, "SLOVENIA");
/* 113 */     createCountryCodeRange(385, 385, "CROATIA");
/* 114 */     createCountryCodeRange(387, 387, "BOSNIA AND HERZEGOVINA");
/* 115 */     createCountryCodeRange(389, 389, "MONTENEGRO");
/* 116 */     createCountryCodeRange(400, 440, "GERMANY");
/* 117 */     createCountryCodeRange(450, 459, "JAPAN");
/* 118 */     createCountryCodeRange(460, 469, "RUSSIA");
/* 119 */     createCountryCodeRange(470, 470, "KYRGYZSTAN");
/* 120 */     createCountryCodeRange(471, 471, "TAIWAN");
/* 121 */     createCountryCodeRange(474, 474, "ESTONIA");
/* 122 */     createCountryCodeRange(475, 475, "LATVIA");
/* 123 */     createCountryCodeRange(476, 476, "AZERBAIJAN");
/* 124 */     createCountryCodeRange(477, 477, "LITHUANIA");
/* 125 */     createCountryCodeRange(478, 478, "UZBEKISTAN");
/* 126 */     createCountryCodeRange(479, 479, "SRI LANKA");
/* 127 */     createCountryCodeRange(480, 480, "PHILIPPINES");
/* 128 */     createCountryCodeRange(481, 481, "BELARUS");
/* 129 */     createCountryCodeRange(482, 482, "UKRAINE");
/* 130 */     createCountryCodeRange(483, 483, "TURKMENISTAN");
/* 131 */     createCountryCodeRange(484, 484, "MOLDOVA");
/* 132 */     createCountryCodeRange(485, 485, "ARMENIA");
/* 133 */     createCountryCodeRange(486, 486, "GEORGIA");
/* 134 */     createCountryCodeRange(487, 487, "KAZAKHSTAN");
/* 135 */     createCountryCodeRange(488, 488, "TAJIKISTAN");
/* 136 */     createCountryCodeRange(489, 489, "HONG KONG");
/* 137 */     createCountryCodeRange(490, 499, "JAPAN");
/* 138 */     createCountryCodeRange(500, 509, "UNITED KINGDOM");
/* 139 */     createCountryCodeRange(520, 521, "GREECE");
/* 140 */     createCountryCodeRange(528, 528, "LEBANON");
/* 141 */     createCountryCodeRange(529, 529, "CYPRUS");
/* 142 */     createCountryCodeRange(530, 530, "ALBANIA");
/* 143 */     createCountryCodeRange(531, 531, "MACEDONIA");
/* 144 */     createCountryCodeRange(535, 535, "MALTA");
/* 145 */     createCountryCodeRange(539, 539, "REPUBLIC OF IRELAND");
/* 146 */     createCountryCodeRange(540, 549, "BELGIUM AND LUXEMBOURG");
/* 147 */     createCountryCodeRange(560, 560, "PORTUGAL");
/* 148 */     createCountryCodeRange(569, 569, "ICELAND");
/* 149 */     createCountryCodeRange(570, 579, "DENMARK, FAROE ISLANDS AND GREENLAND");
/* 150 */     createCountryCodeRange(590, 590, "POLAND");
/* 151 */     createCountryCodeRange(594, 594, "ROMANIA");
/* 152 */     createCountryCodeRange(599, 599, "HUNGARY");
/* 153 */     createCountryCodeRange(600, 601, "SOUTH AFRICA");
/* 154 */     createCountryCodeRange(603, 603, "GHANA");
/* 155 */     createCountryCodeRange(604, 604, "SENEGAL");
/* 156 */     createCountryCodeRange(608, 608, "BAHRAIN");
/* 157 */     createCountryCodeRange(609, 609, "MAURITIUS");
/* 158 */     createCountryCodeRange(611, 611, "MOROCCO");
/* 159 */     createCountryCodeRange(613, 613, "ALGERIA");
/* 160 */     createCountryCodeRange(615, 615, "NIGERIA");
/* 161 */     createCountryCodeRange(616, 616, "KENYA");
/* 162 */     createCountryCodeRange(618, 618, "IVORY COAST");
/* 163 */     createCountryCodeRange(619, 619, "TUNISIA");
/* 164 */     createCountryCodeRange(620, 620, "TANZANIA");
/* 165 */     createCountryCodeRange(621, 621, "SYRIA");
/* 166 */     createCountryCodeRange(622, 622, "EGYPT");
/* 167 */     createCountryCodeRange(623, 623, "BRUNEI");
/* 168 */     createCountryCodeRange(624, 624, "LIBYA");
/* 169 */     createCountryCodeRange(625, 625, "JORDAN");
/* 170 */     createCountryCodeRange(626, 626, "IRAN");
/* 171 */     createCountryCodeRange(627, 627, "KUWAIT");
/* 172 */     createCountryCodeRange(628, 628, "SAUDI ARABIA");
/* 173 */     createCountryCodeRange(629, 629, "UNITED ARAB EMIRATES");
/* 174 */     createCountryCodeRange(640, 649, "FINLAND");
/* 175 */     createCountryCodeRange(690, 699, "CHINA");
/* 176 */     createCountryCodeRange(700, 709, "NORWAY");
/* 177 */     createCountryCodeRange(729, 729, "ISRAEL");
/* 178 */     createCountryCodeRange(730, 739, "SWEDEN");
/* 179 */     createCountryCodeRange(740, 740, "GUATEMALA");
/* 180 */     createCountryCodeRange(741, 741, "EL SALVADOR");
/* 181 */     createCountryCodeRange(742, 742, "HONDURAS");
/* 182 */     createCountryCodeRange(743, 743, "NICARAGUA");
/* 183 */     createCountryCodeRange(744, 744, "COSTA RICA");
/* 184 */     createCountryCodeRange(745, 745, "PANAMA");
/* 185 */     createCountryCodeRange(746, 746, "DOMINICAN REPUBLIC");
/* 186 */     createCountryCodeRange(750, 750, "MEXICO");
/* 187 */     createCountryCodeRange(754, 755, "CANADA");
/* 188 */     createCountryCodeRange(759, 759, "VENEZUELA");
/* 189 */     createCountryCodeRange(760, 769, "SWITZERLAND AND LIECHTENSTEIN");
/* 190 */     createCountryCodeRange(770, 771, "COLOMBIA");
/* 191 */     createCountryCodeRange(773, 773, "URUGUAY");
/* 192 */     createCountryCodeRange(775, 775, "PERU");
/* 193 */     createCountryCodeRange(777, 777, "BOLIVIA");
/* 194 */     createCountryCodeRange(778, 779, "ARGENTINA");
/* 195 */     createCountryCodeRange(780, 780, "CHILE");
/* 196 */     createCountryCodeRange(784, 784, "PARAGUAY");
/* 197 */     createCountryCodeRange(786, 786, "ECUADOR");
/* 198 */     createCountryCodeRange(789, 790, "BRAZIL");
/* 199 */     createCountryCodeRange(800, 839, "ITALY, SAN MARINO AND VATICAN CITY");
/* 200 */     createCountryCodeRange(840, 849, "SPAIN AND ANDORRA");
/* 201 */     createCountryCodeRange(850, 850, "CUBA");
/* 202 */     createCountryCodeRange(858, 858, "SLOVAKIA");
/* 203 */     createCountryCodeRange(859, 859, "CZECH REPUBLIC");
/* 204 */     createCountryCodeRange(860, 860, "SERBIA");
/* 205 */     createCountryCodeRange(865, 865, "MONGOLIA");
/* 206 */     createCountryCodeRange(867, 867, "NORTH KOREA");
/* 207 */     createCountryCodeRange(868, 869, "TURKEY");
/* 208 */     createCountryCodeRange(870, 879, "NETHERLANDS");
/* 209 */     createCountryCodeRange(880, 880, "SOUTH KOREA");
/* 210 */     createCountryCodeRange(884, 884, "CAMBODIA");
/* 211 */     createCountryCodeRange(885, 885, "THAILAND");
/* 212 */     createCountryCodeRange(888, 888, "SINGAPORE");
/* 213 */     createCountryCodeRange(890, 890, "INDIA");
/* 214 */     createCountryCodeRange(893, 893, "VIETNAM");
/* 215 */     createCountryCodeRange(896, 896, "PAKISTAN");
/* 216 */     createCountryCodeRange(899, 899, "INDONESIA");
/* 217 */     createCountryCodeRange(900, 919, "AUSTRIA");
/* 218 */     createCountryCodeRange(930, 939, "AUSTRALIA");
/* 219 */     createCountryCodeRange(940, 949, "NEW ZEALAND");
/* 220 */     createCountryCodeRange(950, 950, "GS1 GLOBAL OFFICE SPECIAL APPLICATIONS");
/* 221 */     createCountryCodeRange(951, 951, "EPC GLOBAL SPECIAL APPLICATIONS");
/* 222 */     createCountryCodeRange(955, 955, "MALAYSIA");
/* 223 */     createCountryCodeRange(958, 958, "MACAU");
/* 224 */     createCountryCodeRange(960, 961, "GS1 UK OFFICE: GTIN-8 ALLOCATIONS");
/* 225 */     createCountryCodeRange(962, 969, "GS1 GLOBAL OFFICE: GTIN-8 ALLOCATIONS");
/* 226 */     createCountryCodeRange(977, 977, "SERIAL PUBLICATIONS (ISSN)");
/* 227 */     createCountryCodeRange(978, 979, "BOOKLAND (ISBN) 979-0 USED FOR SHEET MUSIC (ISMN-13, REPLACES DEPRECATED ISMN M- NUMBERS)");
/* 228 */     createCountryCodeRange(980, 980, "REFUND RECEIPTS");
/* 229 */     createCountryCodeRange(981, 984, "GS1 COUPON IDENTIFICATION FOR COMMON CURRENCY AREAS");
/* 230 */     createCountryCodeRange(990, 999, "GS1 COUPON IDENTIFICATION");
/*     */   }
/*     */   
/*     */   private void calculateCheckDigit() {
/*     */     try {
/* 235 */       String rawDataHolder = getRawData().substring(0, 12);
/*     */       
/* 237 */       int even = 0;
/* 238 */       int odd = 0;
/*     */       
/* 240 */       for (int i = 0; i < rawDataHolder.length(); i++) {
/* 241 */         if (i % 2 == 0) {
/* 242 */           odd += Integer.parseInt(rawDataHolder.substring(i, i + 1));
/*     */         } else {
/* 244 */           even += Integer.parseInt(rawDataHolder.substring(i, i + 1)) * 3;
/*     */         } 
/*     */       } 
/*     */       
/* 248 */       int total = even + odd;
/* 249 */       int cs = total % 10;
/* 250 */       cs = 10 - cs;
/* 251 */       if (cs == 10) {
/* 252 */         cs = 0;
/*     */       }
/*     */       
/* 255 */       setRawData(rawDataHolder + String.valueOf(cs).toCharArray()[0]);
/* 256 */     } catch (Exception ex) {
/* 257 */       error("EEAN13-4: Error calculating check digit.");
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getEncodedValue() {
/* 262 */     return encodeEAN13();
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\com\pnuema\java\barcode\symbologies\EAN13.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */