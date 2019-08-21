package com.icterra.jira.plugins.jira.customfields;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.customfields.converters.DoubleConverter;
import com.atlassian.jira.issue.customfields.impl.NumberCFType;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;

import javax.annotation.Nonnull;
import java.util.Currency;
import java.util.Map;

@Scanned
public class CurrencyCFType extends NumberCFType {

    //private static final Logger log = LoggerFactory.getLogger(CurrencyCFType.class);

    public CurrencyCFType(final CustomFieldValuePersister customFieldValuePersister,
                          final DoubleConverter doubleConverter,
                          final GenericConfigManager genericConfigManager) {
        super(customFieldValuePersister,
                doubleConverter,
                genericConfigManager);
    }


    @Nonnull
    @Override
    public Map<String, Object> getVelocityParameters(final Issue issue,
                                                     final CustomField field,
                                                     final FieldLayoutItem fieldLayoutItem) {

        final Map<String, Object> map = super.getVelocityParameters(issue, field, fieldLayoutItem);

        String symbol = Currency.getInstance(getI18nBean().getLocale()).getSymbol();
        map.put("currencySymbol", symbol);
        log.debug("Currency symbol is" + symbol);
        return map;
    }
}