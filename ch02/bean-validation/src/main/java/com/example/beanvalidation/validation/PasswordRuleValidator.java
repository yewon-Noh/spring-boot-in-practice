package com.example.beanvalidation.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;

import java.util.List;
import java.util.ArrayList;

/**
 * 비밀번호 유효성 검증 로직 구현
 */
public class PasswordRuleValidator implements ConstraintValidator<Password, String> {

    private static final int MIN_COMPLEX_RULES = 2;
    private static final int MAX_REPETITIVE_CHARS = 3;
    private static final int MIN_SPECIAL_CASE_CHARS = 1;
    private static final int MIN_UPPER_CASE_CHARS = 1;
    private static final int MIN_LOWER_CASE_CHARS = 1;
    private static final int MIN_DIGIT_CASE_CHARS = 1;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        List<Rule> passwordRules = new ArrayList<>();
        passwordRules.add(new LengthRule(8, 30));  // 최소 8자, 최대 30자
        CharacterCharacteristicsRule characterCharacteristicsRule =
                new CharacterCharacteristicsRule(MIN_COMPLEX_RULES,
                        new CharacterRule(EnglishCharacterData.Special, MIN_SPECIAL_CASE_CHARS),
                        new CharacterRule(EnglishCharacterData.UpperCase, MIN_UPPER_CASE_CHARS),
                        new CharacterRule(EnglishCharacterData.LowerCase, MIN_LOWER_CASE_CHARS),
                        new CharacterRule(EnglishCharacterData.Digit, MIN_DIGIT_CASE_CHARS)
                );  // 대소문자와 숫자가 혼합돼 있어야 함
        passwordRules.add(characterCharacteristicsRule);
        passwordRules.add(new RepeatCharacterRegexRule(MAX_REPETITIVE_CHARS));  // 동일 문자는 3번까지 가능
        PasswordValidator passwordValidator = new PasswordValidator(passwordRules);
        PasswordData passwordData = new PasswordData(password);
        RuleResult ruleResult = passwordValidator.validate(passwordData);
        return ruleResult.isValid();
    }
}
