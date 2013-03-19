package com.dewald.brent;

import java.text.NumberFormat;

/* Author: Brent Dewald
 * 04-12-2011
 * 
 * This program will calculate the shim that is needed to set the proper pinion depth when using the pinion
 * depth tool from Randy's Ring and Pinion. The program will need the user to input the reading from the dial 
 * indicator and what differential they are using. The user will also have to specify what attachments they are 
 * using on the tool. 
 * 
 */

public class ShimCalculator {

	//Constants for the three different extensions used on the dial indicator
	private final double SHORT_EXT = 1.9;
	private final double MED_EXT = 2.5;
	private final double LONG_EXT = 3.375;
	
	//Constants for Master Housing Depth for use in calculations
	private final double GM10BOLT72 = 3.693;
        private final double GM10BOLT75 = 3.787;
        private final double GM10BOLT82 = 4.175;
        private final double GM10BOLT82VETTE = 4.125;
        private final double GM10BOLT85 = 4.262;
        private final double GM12BOLTSP = 4.556;
        private final double GM12BOLTLP = 4.670;
        private final double GM12BOLT93 = 4.620;
        private final double CRY10BOLT = 4.124;
        private final double CRY12BOLTSP = 4.350;
        private final double CRY12BOLTLP = 4.344;
        private final double CRY12BOLT9250 = 4.625;
        private final double FORD6625 = 3.500;
        private final double FORD75 = 4.040;
        private final double FORD8 = 4.000;
        private final double FORD88 = 4.415;
        private final double FORD9 = 4.375;
        private final double AMC8BOLT = 4.500;
        private final double DS30 = 3.625;
        private final double DS36 = 3.931;
        private final double DS44 = 4.312;
        private final double DS50 = 4.616;
        private final double DS60 = 5.000;
        private final double DS70 = 5.375;


	//Variables that need user input
	private double dialReading = 0.0;
	private double extUsed = 0.0;
	private double shim = 0.0;
	private double masterHousingDepth = 0.0;
	private double pinionHeight = 0.0;
	private double pinionDepth = 0.0;
	private double measuredPinionDepth = 0.0;
	private double blockHeight = 0.0;
	private double measuredHousingDepth = 0.0;

        //this contructor is for the OEM Calculations
        public ShimCalculator(int diff, double pinionHeight, int ext, double reading){
                this.masterHousingDepth = DiffChoice(diff);
                this.pinionHeight = pinionHeight;
                this.extUsed = ExtChoice(ext);
                this.dialReading = reading;
                this.shim = OEMCalculation();
        }//end constructor
	//this constructor is for the AfterMarket calculations
        public ShimCalculator(int ext, double reading, double scribedDepth){
                this.extUsed = ExtChoice(ext);
                this.dialReading = reading;
                this.pinionDepth = scribedDepth;
                this.shim = AfterMarketCalculation();
        }//end constructor
        //this constructor is for use for the 1-2-3 block method
        public ShimCalculator(int diff, int ext, double reading, int block){
                this.masterHousingDepth = DiffChoice(diff);
                this.extUsed = ExtChoice(ext);
                this.dialReading = reading;
                this.blockHeight = block + 1;
                this.shim = BlockMethod();
        }//end constructor
	private double OEMCalculation(){
		
		pinionDepth = masterHousingDepth - pinionHeight;
		measuredPinionDepth = extUsed - dialReading;
		shim = measuredPinionDepth - pinionDepth;
		
		return Math.abs(shim);
	}//end OEMCalculation
	private double AfterMarketCalculation(){
		
		measuredPinionDepth = extUsed - dialReading;
		shim = measuredPinionDepth - pinionDepth;
		
		return Math.abs(shim);
	}//end AfterMarketClaculation
	private double BlockMethod(){
		
		measuredHousingDepth = extUsed - dialReading + blockHeight;
		shim = masterHousingDepth - measuredHousingDepth;
		
		return Math.abs(shim);
	}//end BlockMethod

        private double ExtChoice(int ext){
                switch (ext){
                    case 0: extUsed = SHORT_EXT;
                    break;
                    case 1: extUsed = MED_EXT;
                    break;
                    case 2: extUsed = LONG_EXT;
                    break;
                    default: break;

                }//end switch
                return extUsed;
        }//end ExtChoice

        private double DiffChoice(int diff){
            switch (diff){
                case 0: masterHousingDepth = GM10BOLT72;
                break;
                case 1: masterHousingDepth = GM10BOLT75;
                break;
                case 2: masterHousingDepth = GM10BOLT82;
                break;
                case 3: masterHousingDepth = GM10BOLT82;
                break;
                case 4: masterHousingDepth = GM10BOLT82VETTE;
                break;
                case 5: masterHousingDepth =  GM10BOLT85;
                break;
                case 6: masterHousingDepth =  GM10BOLT85;
                break;
                case 7: masterHousingDepth = GM12BOLTSP;
                break;
                case 8: masterHousingDepth = GM12BOLTLP;
                break;
                case 9: masterHousingDepth = GM12BOLT93;
                break;
                case 10: masterHousingDepth = CRY10BOLT;
                break;
                case 11: masterHousingDepth = CRY12BOLTSP;
                break;
                case 12: masterHousingDepth = CRY12BOLTLP;
                break;
                case 13: masterHousingDepth = CRY12BOLT9250;
                break;
                case 14: masterHousingDepth = FORD6625;
                break;
                case 15: masterHousingDepth = FORD75;
                break;
                case 16: masterHousingDepth = FORD8;
                break;
                case 17: masterHousingDepth = FORD88;
                break;
                case 18: masterHousingDepth = FORD9;
                break;
                case 19: masterHousingDepth = AMC8BOLT;
                break;
                case 20: masterHousingDepth = DS30;
                break;
                case 21: masterHousingDepth = DS36;
                break;
                case 22: masterHousingDepth = DS44;
                break;
                case 23: masterHousingDepth = DS50;
                break;
                case 24: masterHousingDepth = DS60;
                break;
                case 25: masterHousingDepth = DS70;
                break;
                default: break;
            }//end switch
            return masterHousingDepth;
        }//end DiffChoice

        public String getShim(){
            NumberFormat nf = NumberFormat.getInstance();
            nf.setMaximumFractionDigits(3);
	    nf.setMinimumFractionDigits(3);
            return nf.format(shim);
        }
}//end class
