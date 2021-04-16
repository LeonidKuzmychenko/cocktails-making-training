package lk.server.cocktails.customtypes.locale;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class LocaleService {

    public String getStringByLocale(Collection<? extends ILocalization> collection, Locale locale) {
        for (ILocalization item : collection)
            if (item.getLocale() == locale)
                return item.getName();
        return null;
    }

    public String getEnString(Collection<? extends ILocalization> collection) {
        return getStringByLocale(collection, Locale.EN);
    }
}
