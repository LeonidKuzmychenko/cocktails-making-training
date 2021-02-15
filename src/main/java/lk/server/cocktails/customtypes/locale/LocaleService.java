package lk.server.cocktails.customtypes.locale;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class LocaleService {

//    public String getStringByLocale(List<ILocalization> collection, Locale locale) {
//        for (ILocalization item : collection)
//            if (item.getLocale() == locale)
//                return item.getName();
//        return null;
//    }

//    public String getStringByLocale(Set<ILocalization> collection, Locale locale) {
//        return getStringByLocale(new ArrayList<>(collection), locale);
//    }

    public String getStringByLocale(Collection<ILocalization> collection, Locale locale) {
        for (ILocalization item : collection)
            if (item.getLocale() == locale)
                return item.getName();
        return null;
    }
}
