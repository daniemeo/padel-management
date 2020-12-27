package it.solvingteam.padelmanagement.model.slot;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Slot {
//	OTTO(1,8,0),
//	OTTO_E_TRENTA(2,8,30),
//	NOVE(3,9,0),
//	NOVE_E_TRENTA(4,9,30),
//	DIECI(5,10,0),
//	DIECI_E_TRENTA(6,10,30),
//	UNDICI(7,11,0),
//	UNDICI_E_TRENTA(8,11,30),
//	DODICI(9,12,0),
//	DODICI_E_TRENTA(10,12,30),
//	TREDICI(11,13,0),
//	TREDICI_E_TRENTA(12,13,30),
//	QUATTORDICI(13,14,0),
//	QUATTORDICI_E_TRENTA(14,14,30),
//	QUINDICI(15,15,0),
//	QUINDICI_E_TRENTA(16,15,30),
//	SEDICI(17,16,0),
//	SEDICI_E_TRENTA(18,16,30),
//	DICIASSETTE(19,17,0),
//	DICIASSETTE_E_TRENTA(20,17,30),
//	DICIOTTO(21,18,0),
//	DICIOTTO_E_TRENTA(22,18,30),
//	DICIANNOVE(23,19,0),
//	DICIANNOVE_E_TRENTA(24,19,30),
//	VENTI(25,20,0),
//	VENTI_E_TRENTA(26,20,30),
//	VENTUNO(27,21,0),
//	VENTUNO_E_TRENTA(28,21,30),
//	VENTIDUE(29,22,0);

	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer hour;
	private Integer minute;
	@Enumerated(EnumType.STRING)
	private SlotName slotName;
	
	Slot(Integer id, Integer hour, Integer minute, SlotName slotName) {
		this.id=id;
		this.hour=hour;
		this.minute=minute;
		this.slotName = slotName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}
	
	
	public SlotName getSlotName() {
		return slotName;
	}

	public void setSlotName(SlotName slotName) {
		this.slotName = slotName;
	}


	public static Map<Integer, Slot> idToSlotConversion = new HashMap<>();
	
//	static {
//		idToSlotConversion.put(1,OTTO);
//		idToSlotConversion.put(2,OTTO_E_TRENTA);
//		idToSlotConversion.put(3,NOVE);
//		idToSlotConversion.put(4,NOVE_E_TRENTA);
//		idToSlotConversion.put(5,DIECI);
//		idToSlotConversion.put(6,DIECI_E_TRENTA);
//		idToSlotConversion.put(7,UNDICI);
//		idToSlotConversion.put(8,UNDICI_E_TRENTA);
//		idToSlotConversion.put(9,DODICI);
//		idToSlotConversion.put(10,DODICI_E_TRENTA);
//		idToSlotConversion.put(11,TREDICI);
//		idToSlotConversion.put(12,TREDICI_E_TRENTA);
//		idToSlotConversion.put(13,QUATTORDICI);
//		idToSlotConversion.put(14,QUATTORDICI_E_TRENTA);
//		idToSlotConversion.put(15,QUINDICI);
//		idToSlotConversion.put(16,QUINDICI_E_TRENTA);
//		idToSlotConversion.put(17,SEDICI);
//		idToSlotConversion.put(18,SEDICI_E_TRENTA);
//		idToSlotConversion.put(19,DICIASSETTE);
//		idToSlotConversion.put(20,DICIASSETTE_E_TRENTA);
//		idToSlotConversion.put(21,DICIOTTO);
//		idToSlotConversion.put(22,DICIOTTO_E_TRENTA);
//		idToSlotConversion.put(23,DICIANNOVE);
//		idToSlotConversion.put(24,DICIANNOVE_E_TRENTA);
//		idToSlotConversion.put(25,VENTI);
//		idToSlotConversion.put(26,VENTI_E_TRENTA);
//		idToSlotConversion.put(27,VENTUNO);
//		idToSlotConversion.put(28,VENTUNO_E_TRENTA);
//		idToSlotConversion.put(29,VENTIDUE);
//	}
	
	public static Slot convertIdToSlot(Integer id) {
		return idToSlotConversion.get(id);
	}
	
	public static SlotName convertHourAndMinuteToSlot (Integer hour,Integer minute) {
		switch (hour) {
			case 8: 
				if(minute==0) {
					return SlotName.OTTO;
				} else if(minute==30) {
					return SlotName.OTTO_E_TRENTA;
				} else {
					return null;
				}
				
			case 9:
				if(minute==0) {
					return SlotName.NOVE;
				} else if(minute==30) {
					return SlotName.NOVE_E_TRENTA;
				} else {
					return null;
				}
				
			case 10:
				if(minute==0) {
					return SlotName.DIECI;
				} else if(minute==30) {
					return SlotName.DIECI_E_TRENTA;
				} else {
					return null;
				}
			
			case 11:
				if(minute==0) {
					return SlotName.UNDICI;
				} else if(minute==30) {
					return SlotName.UNDICI_E_TRENTA;
				} else {
					return null;
				}
				
			case 12:
				if(minute==0) {
					return SlotName.DODICI;
				} else if(minute==30) {
					return SlotName.DODICI_E_TRENTA;
				} else {
					return null;
				}
				
			case 13:
				if(minute==0) {
					return SlotName.TREDICI;
				} else if(minute==30) {
					return SlotName.TREDICI_E_TRENTA;
				} else {
					return null;
				}
				
			case 14:
				if(minute==0) {
					return SlotName.QUATTORDICI;
				} else if(minute==30) {
					return SlotName.QUATTORDICI_E_TRENTA;
				} else {
					return null;
				}
				
			case 15:
				if(minute==0) {
					return SlotName.QUINDICI;
				} else if(minute==30) {
					return SlotName.QUINDICI_E_TRENTA;
				} else {
					return null;
				}
				
			case 16:
				if(minute==0) {
					return SlotName.SEDICI;
				} else if(minute==30) {
					return SlotName.SEDICI_E_TRENTA;
				} else {
					return null;
				}
				
			case 17:
				if(minute==0) {
					return SlotName.DICIASSETTE;
				} else if(minute==30) {
					return SlotName.DICIASSETTE_E_TRENTA;
				} else {
					return null;
				}
				
			case 18:
				if(minute==0) {
					return SlotName.DICIOTTO;
				} else if(minute==30) {
					return SlotName.DICIOTTO_E_TRENTA;
				} else {
					return null;
				}
				
			case 19:
				if(minute==0) {
					return SlotName.DICIANNOVE;
				} else if(minute==30) {
					return SlotName.DICIANNOVE_E_TRENTA;
				} else {
					return null;
				}
				
			case 20:
				if(minute==0) {
					return SlotName.VENTI;
				} else if(minute==30) {
					return SlotName.VENTI_E_TRENTA;
				} else {
					return null;
				}
				
			case 21:
				if(minute==0) {
					return SlotName.VENTUNO;
				} else if(minute==30) {
					return SlotName.VENTUNO_E_TRENTA;
				} else {
					return null;
				}
				
			case 22:
				if(minute==0) {
					return SlotName.VENTIDUE;
				} else {
					return null;
				}
			
			default: 
				return null;
		} 
	}
}