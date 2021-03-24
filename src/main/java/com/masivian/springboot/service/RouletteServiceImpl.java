package com.masivian.springboot.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.masivian.springboot.entity.Bet;
import com.masivian.springboot.entity.Customer;
import com.masivian.springboot.entity.Roulette;
import com.masivian.springboot.enums.ColorEnum;
import com.masivian.springboot.enums.TypeBetEnum;
import com.masivian.springboot.repository.CustomerRepository;
import com.masivian.springboot.repository.GenericRepository;
import com.masivian.springboot.repository.RouletteRepository;
import com.masivian.springboot.utilities.GenerateRandomNumber;
import com.masivian.springboot.utilities.IsNumeric;

@Service
@Transactional
public class RouletteServiceImpl implements IRouletteService {

	@Autowired
	private RouletteRepository rouletteRepository;
	@Autowired
	private CustomerRepository customerRepository;
	private static final String ROJO = "ROJO";
	private static final String NEGRO = "NEGRO";

	public Roulette save(Roulette roulette) {
		String id = UUID.randomUUID().toString().replace("-", "");
		if(roulette == null) {
			roulette = new Roulette();
			roulette.setStatus(false);
			roulette.setId(id);
		}
			
		return rouletteRepository.save(roulette);
	}

	public List<Roulette> findAll() {
		return rouletteRepository.findAll();
	}

	public Roulette opening(String id) throws Exception {
		Roulette roulette = this.findById(id);
		if (roulette.isStatus())
			throw new Exception("El estado de la ruleta está activo");
		if (roulette.getBet() != null && !roulette.getBet().isEmpty())
			roulette.getBet().clear();
		roulette.setStatus(true);
		rouletteRepository.update(roulette);
		return roulette;
	}

	public Roulette bet(String rouletteId, String customerId, Bet bet) throws Exception {
		Customer customer = (Customer) customerRepository.findById(customerId);
		TypeBetEnum typeBetEnum = this.getTypeBet(bet);
		if(customer == null)
			throw new Exception("No existe el cliente");
		if(customer.getWallet() < bet.getMoney())
			throw new Exception("El cliente no posee fondos suficientes");
		Roulette roulette = this.findById(rouletteId);
		if(bet.getMoney() < 1 && bet.getMoney() > 10000)
			throw new Exception("El dinero de la apuesta no se encuentra entre 1 y 10000");
		if(!roulette.isStatus())
			throw new Exception("La ruleta se encuentra cerrada");
		if(!TypeValueBetEnum(bet.getItemBet(), typeBetEnum))
			throw new Exception("El dato de la apuesta es incorrecto");
		roulette = buildListBet(roulette, bet, customer);
		
		return save(roulette);
	}

	public Roulette buildListBet(Roulette roulette, Bet bet, Customer customer){
		bet.setCustomer(customer);
		roulette.getBet().add(bet);
		return roulette;
	}
	
	public TypeBetEnum getTypeBet(Bet bet) {
		IsNumeric isNumeric = new IsNumeric(bet.getItemBet());
		if (isNumeric.isNumeric())
			return TypeBetEnum.NUMBER;

		return TypeBetEnum.COLOR;
	}
	
	public boolean TypeValueBetEnum(String value, TypeBetEnum typeBetEnum) {
		int number = -1;
		if (typeBetEnum.equals(TypeBetEnum.NUMBER)) {
			number = Integer.parseInt(value);
			return number >= 0 && number <= 36;
		}
		return value.equalsIgnoreCase(ROJO) || value.equalsIgnoreCase(NEGRO);

	}

	public Roulette closing(String id) throws Exception {
		GenerateRandomNumber randomNumber = new GenerateRandomNumber(36, 0);
		Roulette roulette = this.findById(id);
		if (!roulette.isStatus())
			throw new Exception("La ruleta ya ha sido cerrada");
		roulette.setStatus(false);
		roulette = buildBetClosing(roulette, GenerateRandomNumber.generateRandomNumber());
		rouletteRepository.update(roulette);
		return roulette;
	}
	public Roulette buildBetClosing(Roulette roulette, int resultBet) {
		ColorEnum colorEnum = ColorEnum.NEGRO;
		if(resultBet % 2 == 0)
			colorEnum = ColorEnum.ROJO;
		for(Bet bet: roulette.getBet())
			betResult(bet,resultBet, colorEnum);
		return roulette;
	}

	public void betResult(Bet bet, int resultBet, ColorEnum colorEnum){
		bet.setResultBet(String.valueOf(resultBet));
		if(colorEnum.getValueBet().equals(bet.getItemBet()))
			bet.getCustomer().setWallet(bet.getCustomer().getWallet() + (bet.getMoney() * 1.8));
		else if(bet.getResultBet().equals(bet.getItemBet()))
			bet.getCustomer().setWallet(bet.getCustomer().getWallet() + (bet.getMoney() * 5));
		else {
			bet.getCustomer().setWallet(bet.getCustomer().getWallet() - bet.getMoney());
		}
		customerRepository.update(bet.getCustomer());
	}

	public Roulette findById(String id) throws Exception {
		Roulette roulette = rouletteRepository.findById(id);
		if (id == null)
			throw new Exception("No ha enviado un id");
		if (roulette == null)
			throw new Exception("No se ha encontrado la ruleta");
		return roulette;
	}

	public String delete(String id) throws Exception {
		this.findById(id);
		rouletteRepository.delete(id);
		return "Eliminado correctamente";
	}

}
