package com.masivian.springboot.repository;

import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.masivian.springboot.entity.Roulette;

@Repository
public class RouletteRepository extends GenericRepository<Roulette>{

	public Roulette save(Roulette roulette) {
		getTemplate().opsForHash().put(this.getTypeName().getSimpleName(), roulette.getId(), roulette);
		return roulette;
	}
	public Roulette update(Roulette roulette) {
		save(roulette);
		return roulette;
	}
}
