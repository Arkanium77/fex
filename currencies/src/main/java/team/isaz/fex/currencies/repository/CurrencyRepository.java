package team.isaz.fex.currencies.repository;

import org.springframework.data.repository.CrudRepository;
import team.isaz.fex.currencies.domain.Currency;

public interface CurrencyRepository extends CrudRepository<Currency,Integer> {
    Currency findFirstByName(String name);
}
