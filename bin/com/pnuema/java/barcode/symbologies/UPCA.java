/*     */ package com.pnuema.java.barcode.symbologies;
/*     */ 
/*     */ import com.pnuema.java.barcode.BarcodeCommon;
/*     */ import com.pnuema.java.barcode.IBarcode;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ public class UPCA
/*     */   extends BarcodeCommon
/*     */   implements IBarcode
/*     */ {
/*  12 */   private final String[] UPC_CodeA = new String[] { "0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011", "0110111", "0001011" };
/*  13 */   private final String[] UPC_CodeB = new String[] { "1110010", "1100110", "1101100", "1000010", "1011100", "1001110", "1010000", "1000100", "1001000", "1110100" };
/*  14 */   private String countryAssigningManufacturerCode = "N/A";
/*  15 */   private final HashMap<String, String> countryCodes = new HashMap<>();
/*     */   
/*     */   public UPCA(String input) {
/*  18 */     setRawData(input);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String encodeUPCA() {
/*  28 */     if (getRawData().length() != 11 && getRawData().length() != 12) {
/*  29 */       error("EUPCA-1: Data length invalid. (Length must be 11 or 12)");
/*     */     }
/*     */ 
/*     */     
/*  33 */     if (!checkNumericOnly(getRawData())) {
/*  34 */       error("EUPCA-2: Numeric Data Only");
/*     */     }
/*     */     
/*  37 */     calculateCheckDigit();
/*     */     
/*  39 */     StringBuilder result = new StringBuilder("101");
/*     */ 
/*     */     
/*  42 */     result.append(this.UPC_CodeA[Integer.parseInt(String.valueOf(getRawData().toCharArray()[0]))]);
/*     */ 
/*     */     
/*  45 */     int pos = 0;
/*  46 */     while (pos < 5) {
/*  47 */       result.append(this.UPC_CodeA[Integer.parseInt(String.valueOf(getRawData().toCharArray()[pos + 1]))]);
/*  48 */       pos++;
/*     */     } 
/*     */ 
/*     */     
/*  52 */     result.append("01010");
/*     */ 
/*     */     
/*  55 */     pos = 0;
/*  56 */     while (pos < 5) {
/*  57 */       result.append(this.UPC_CodeB[Integer.parseInt(String.valueOf(getRawData().toCharArray()[pos++ + 6]))]);
/*     */     }
/*     */ 
/*     */     
/*  61 */     result.append(this.UPC_CodeB[Integer.parseInt(String.valueOf(getRawData().toCharArray()[getRawData().length() - 1]))]);
/*     */ 
/*     */     
/*  64 */     result.append("101");
/*     */ 
/*     */     
/*  67 */     init_CountryCodes();
/*  68 */     String twodigitCode = "0" + getRawData().charAt(0);
/*     */     try {
/*  70 */       this.countryAssigningManufacturerCode = this.countryCodes.get(twodigitCode);
/*  71 */     } catch (Exception ex) {
/*  72 */       error("EUPCA-3: Country assigning manufacturer code not found.");
/*     */     } finally {
/*  74 */       this.countryCodes.clear();
/*     */     } 
/*     */     
/*  77 */     return result.toString();
/*     */   }
/*     */   
/*     */   private void init_CountryCodes() {
/*  81 */     this.countryCodes.clear();
/*  82 */     this.countryCodes.put("00", "US / CANADA");
/*  83 */     this.countryCodes.put("01", "US / CANADA");
/*  84 */     this.countryCodes.put("02", "US / CANADA");
/*  85 */     this.countryCodes.put("03", "US / CANADA");
/*  86 */     this.countryCodes.put("04", "US / CANADA");
/*  87 */     this.countryCodes.put("05", "US / CANADA");
/*  88 */     this.countryCodes.put("06", "US / CANADA");
/*  89 */     this.countryCodes.put("07", "US / CANADA");
/*  90 */     this.countryCodes.put("08", "US / CANADA");
/*  91 */     this.countryCodes.put("09", "US / CANADA");
/*  92 */     this.countryCodes.put("10", "US / CANADA");
/*  93 */     this.countryCodes.put("11", "US / CANADA");
/*  94 */     this.countryCodes.put("12", "US / CANADA");
/*  95 */     this.countryCodes.put("13", "US / CANADA");
/*     */     
/*  97 */     this.countryCodes.put("20", "IN STORE");
/*  98 */     this.countryCodes.put("21", "IN STORE");
/*  99 */     this.countryCodes.put("22", "IN STORE");
/* 100 */     this.countryCodes.put("23", "IN STORE");
/* 101 */     this.countryCodes.put("24", "IN STORE");
/* 102 */     this.countryCodes.put("25", "IN STORE");
/* 103 */     this.countryCodes.put("26", "IN STORE");
/* 104 */     this.countryCodes.put("27", "IN STORE");
/* 105 */     this.countryCodes.put("28", "IN STORE");
/* 106 */     this.countryCodes.put("29", "IN STORE");
/*     */     
/* 108 */     this.countryCodes.put("30", "FRANCE");
/* 109 */     this.countryCodes.put("31", "FRANCE");
/* 110 */     this.countryCodes.put("32", "FRANCE");
/* 111 */     this.countryCodes.put("33", "FRANCE");
/* 112 */     this.countryCodes.put("34", "FRANCE");
/* 113 */     this.countryCodes.put("35", "FRANCE");
/* 114 */     this.countryCodes.put("36", "FRANCE");
/* 115 */     this.countryCodes.put("37", "FRANCE");
/*     */     
/* 117 */     this.countryCodes.put("40", "GERMANY");
/* 118 */     this.countryCodes.put("41", "GERMANY");
/* 119 */     this.countryCodes.put("42", "GERMANY");
/* 120 */     this.countryCodes.put("43", "GERMANY");
/* 121 */     this.countryCodes.put("44", "GERMANY");
/*     */     
/* 123 */     this.countryCodes.put("45", "JAPAN");
/* 124 */     this.countryCodes.put("46", "RUSSIAN FEDERATION");
/* 125 */     this.countryCodes.put("49", "JAPAN (JAN-13)");
/*     */     
/* 127 */     this.countryCodes.put("50", "UNITED KINGDOM");
/* 128 */     this.countryCodes.put("54", "BELGIUM / LUXEMBOURG");
/* 129 */     this.countryCodes.put("57", "DENMARK");
/*     */     
/* 131 */     this.countryCodes.put("64", "FINLAND");
/*     */     
/* 133 */     this.countryCodes.put("70", "NORWAY");
/* 134 */     this.countryCodes.put("73", "SWEDEN");
/* 135 */     this.countryCodes.put("76", "SWITZERLAND");
/*     */     
/* 137 */     this.countryCodes.put("80", "ITALY");
/* 138 */     this.countryCodes.put("81", "ITALY");
/* 139 */     this.countryCodes.put("82", "ITALY");
/* 140 */     this.countryCodes.put("83", "ITALY");
/* 141 */     this.countryCodes.put("84", "SPAIN");
/* 142 */     this.countryCodes.put("87", "NETHERLANDS");
/*     */     
/* 144 */     this.countryCodes.put("90", "AUSTRIA");
/* 145 */     this.countryCodes.put("91", "AUSTRIA");
/* 146 */     this.countryCodes.put("93", "AUSTRALIA");
/* 147 */     this.countryCodes.put("94", "NEW ZEALAND");
/* 148 */     this.countryCodes.put("99", "COUPONS");
/*     */     
/* 150 */     this.countryCodes.put("471", "TAIWAN");
/* 151 */     this.countryCodes.put("474", "ESTONIA");
/* 152 */     this.countryCodes.put("475", "LATVIA");
/* 153 */     this.countryCodes.put("477", "LITHUANIA");
/* 154 */     this.countryCodes.put("479", "SRI LANKA");
/* 155 */     this.countryCodes.put("480", "PHILIPPINES");
/* 156 */     this.countryCodes.put("482", "UKRAINE");
/* 157 */     this.countryCodes.put("484", "MOLDOVA");
/* 158 */     this.countryCodes.put("485", "ARMENIA");
/* 159 */     this.countryCodes.put("486", "GEORGIA");
/* 160 */     this.countryCodes.put("487", "KAZAKHSTAN");
/* 161 */     this.countryCodes.put("489", "HONG KONG");
/*     */     
/* 163 */     this.countryCodes.put("520", "GREECE");
/* 164 */     this.countryCodes.put("528", "LEBANON");
/* 165 */     this.countryCodes.put("529", "CYPRUS");
/* 166 */     this.countryCodes.put("531", "MACEDONIA");
/* 167 */     this.countryCodes.put("535", "MALTA");
/* 168 */     this.countryCodes.put("539", "IRELAND");
/* 169 */     this.countryCodes.put("560", "PORTUGAL");
/* 170 */     this.countryCodes.put("569", "ICELAND");
/* 171 */     this.countryCodes.put("590", "POLAND");
/* 172 */     this.countryCodes.put("594", "ROMANIA");
/* 173 */     this.countryCodes.put("599", "HUNGARY");
/*     */     
/* 175 */     this.countryCodes.put("600", "SOUTH AFRICA");
/* 176 */     this.countryCodes.put("601", "SOUTH AFRICA");
/* 177 */     this.countryCodes.put("609", "MAURITIUS");
/* 178 */     this.countryCodes.put("611", "MOROCCO");
/* 179 */     this.countryCodes.put("613", "ALGERIA");
/* 180 */     this.countryCodes.put("619", "TUNISIA");
/* 181 */     this.countryCodes.put("622", "EGYPT");
/* 182 */     this.countryCodes.put("625", "JORDAN");
/* 183 */     this.countryCodes.put("626", "IRAN");
/* 184 */     this.countryCodes.put("690", "CHINA");
/* 185 */     this.countryCodes.put("691", "CHINA");
/* 186 */     this.countryCodes.put("692", "CHINA");
/*     */     
/* 188 */     this.countryCodes.put("729", "ISRAEL");
/* 189 */     this.countryCodes.put("740", "GUATEMALA");
/* 190 */     this.countryCodes.put("741", "EL SALVADOR");
/* 191 */     this.countryCodes.put("742", "HONDURAS");
/* 192 */     this.countryCodes.put("743", "NICARAGUA");
/* 193 */     this.countryCodes.put("744", "COSTA RICA");
/* 194 */     this.countryCodes.put("746", "DOMINICAN REPUBLIC");
/* 195 */     this.countryCodes.put("750", "MEXICO");
/* 196 */     this.countryCodes.put("759", "VENEZUELA");
/* 197 */     this.countryCodes.put("770", "COLOMBIA");
/* 198 */     this.countryCodes.put("773", "URUGUAY");
/* 199 */     this.countryCodes.put("775", "PERU");
/* 200 */     this.countryCodes.put("777", "BOLIVIA");
/* 201 */     this.countryCodes.put("779", "ARGENTINA");
/* 202 */     this.countryCodes.put("780", "CHILE");
/* 203 */     this.countryCodes.put("784", "PARAGUAY");
/* 204 */     this.countryCodes.put("785", "PERU");
/* 205 */     this.countryCodes.put("786", "ECUADOR");
/* 206 */     this.countryCodes.put("789", "BRAZIL");
/*     */     
/* 208 */     this.countryCodes.put("850", "CUBA");
/* 209 */     this.countryCodes.put("858", "SLOVAKIA");
/* 210 */     this.countryCodes.put("859", "CZECH REPUBLIC");
/* 211 */     this.countryCodes.put("860", "YUGLOSLAVIA");
/* 212 */     this.countryCodes.put("869", "TURKEY");
/* 213 */     this.countryCodes.put("880", "SOUTH KOREA");
/* 214 */     this.countryCodes.put("885", "THAILAND");
/* 215 */     this.countryCodes.put("888", "SINGAPORE");
/* 216 */     this.countryCodes.put("890", "INDIA");
/* 217 */     this.countryCodes.put("893", "VIETNAM");
/* 218 */     this.countryCodes.put("899", "INDONESIA");
/*     */     
/* 220 */     this.countryCodes.put("955", "MALAYSIA");
/* 221 */     this.countryCodes.put("977", "INTERNATIONAL STANDARD SERIAL NUMBER FOR PERIODICALS (ISSN)");
/* 222 */     this.countryCodes.put("978", "INTERNATIONAL STANDARD BOOK NUMBERING (ISBN)");
/* 223 */     this.countryCodes.put("979", "INTERNATIONAL STANDARD MUSIC NUMBER (ISMN)");
/* 224 */     this.countryCodes.put("980", "REFUND RECEIPTS");
/* 225 */     this.countryCodes.put("981", "COMMON CURRENCY COUPONS");
/* 226 */     this.countryCodes.put("982", "COMMON CURRENCY COUPONS");
/*     */   }
/*     */   
/*     */   private void calculateCheckDigit() {
/*     */     try {
/* 231 */       String rawDataHolder = getRawData().substring(0, 11);
/*     */ 
/*     */       
/* 234 */       int sum = 0;
/*     */       
/* 236 */       for (int i = 0; i < rawDataHolder.length(); i++) {
/* 237 */         int parseInt = Integer.parseInt(rawDataHolder.substring(i, i + 1));
/* 238 */         if (i % 2 == 0) {
/* 239 */           sum += parseInt * 3;
/*     */         } else {
/* 241 */           sum += parseInt;
/*     */         } 
/*     */       } 
/*     */       
/* 245 */       int cs = (10 - sum % 10) % 10;
/*     */       
/* 247 */       setRawData(rawDataHolder + String.valueOf(cs).toCharArray()[0]);
/* 248 */     } catch (Exception ex) {
/* 249 */       error("EUPCA-4: Error calculating check digit.");
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getEncodedValue() {
/* 254 */     return encodeUPCA();
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\com\pnuema\java\barcode\symbologies\UPCA.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */