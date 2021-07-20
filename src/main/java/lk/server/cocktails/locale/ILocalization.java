package lk.server.cocktails.locale;

public interface ILocalization extends ILocale {
    String getName();

//    default String getLocaleString(List<ILocalization> collection, Locale locale) {
//        for (ILocalization item : collection) {
//            if (item.getLocale() == locale)
//                return item.getName();
//        }
//        return null;
//    }
//
//    default String getStringByLocale(List<ILocalization> collection, Locale locale) {
//     for (ILocalization name : collection)
//            if (name.getLocale() == locale)
//                return name.getName();
//        return null;
//    }

//    public String getIngredientEnName() {
//        for (IngredientName name : ingredientNames)
//            if (name.getLocale() == Locale.EN)
//                return name.getName();
//        return null;
//    }
}
