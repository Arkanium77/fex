package team.isaz.fex.currencies.service;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import team.isaz.fex.currencies.properties.AppProperties;
import team.isaz.fex.currencies.domain.Currency;
import team.isaz.fex.currencies.repository.CurrencyRepository;

import java.util.List;

@Service
public class CurrencyService {
    private final CurrencyRepository repository;
    private final AppProperties properties;

    public CurrencyService(CurrencyRepository repository, AppProperties properties) {
        this.repository = repository;
        this.properties = properties;
    }

    public Currency createCurrency(String name, Double rate) {
        return repository.save(new Currency().withName(name).withRate(rate));
    }

    public Currency createCurrency(String name, String rateTo, Double rate) {
        Currency rated = repository.findFirstByName(rateTo);
        Currency main = repository.findFirstByName(properties.getMainCurrencyName());
        return createCurrency(name, rated.getRate() * rate * main.getRate());
    }

    public boolean deleteCurrency(Integer id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Currency> getCurrencies() {
        return Lists.newArrayList(repository.findAll());
    }

    public Currency getCurrency(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    public Currency getCurrency(String name) {
        return repository.findFirstByName(name);
    }

}
