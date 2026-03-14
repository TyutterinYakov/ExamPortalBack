package ru.pet.portal.api.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.pet.portal.api.service.GigaChatService;
import ru.pet.portal.api.service.dto.gigachat.GeneratedQuiz;
import ru.pet.portal.api.service.dto.gigachat.QuestionGenerationRequest;

import java.io.File;

@Service
@RequiredArgsConstructor
@Profile("!gigachat")
public class MockGigaChatService implements GigaChatService {
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public GeneratedQuiz generateQuiz(QuestionGenerationRequest request) {
        return objectMapper.readValue("""
                {
                     "title": "Тест по пожарной безопасности (средний уровень)",
                     "description": "Данный тест предназначен для проверки ваших знаний по основам пожарной безопасности.",
                     "questions": [
                         {
                             "content": "Что означает сигнал тревоги 'Пожарная тревога'?",
                             "answers": [
                                 {"reply": "Эвакуация персонала", "correctly": true},
                                 {"reply": "Проведение учения", "correctly": false},
                                 {"reply": "Проверка системы оповещения", "correctly": false}
                             ],
                             "marks": 3,
                             "time": 60
                         },
                         {
                             "content": "Какой класс пожара обозначается буквой A?",
                             "answers": [
                                 {"reply": "Горение твердых веществ", "correctly": true},
                                 {"reply": "Горение газов", "correctly": false},
                                 {"reply": "Горение жидкостей", "correctly": false},
                                 {"reply": "Электротехнические пожары", "correctly": false}
                             ],
                             "marks": 4,
                             "time": 90
                         },
                         {
                             "content": "Какие средства пожаротушения используются для тушения электроустановок под напряжением?",
                             "answers": [
                                 {"reply": "Углекислотные огнетушители", "correctly": true},
                                 {"reply": "Водяные струи", "correctly": false},
                                 {"reply": "Песок", "correctly": false}
                             ],
                             "marks": 3,
                             "time": 60
                         },
                         {
                             "content": "Какое расстояние должно быть между эвакуационными выходами?",
                             "answers": [
                                 {"reply": "Минимальное расстояние — 7 метров", "correctly": true},
                                 {"reply": "Максимальное расстояние — 15 метров", "correctly": false},
                                 {"reply": "Расстояние не регламентировано", "correctly": false}
                             ],
                             "marks": 2,
                             "time": 45
                         },
                         {
                             "content": "Что является основной причиной возникновения пожаров в жилых домах?",
                             "answers": [
                                 {"reply": "Неработоспособность противопожарных систем", "correctly": false},
                                 {"reply": "Несоблюдение правил эксплуатации электроприборов", "correctly": true},
                                 {"reply": "Отсутствие первичных средств пожаротушения", "correctly": false}
                             ],
                             "marks": 3,
                             "time": 60
                         },
                         {
                             "content": "Что включает в себя понятие 'противопожарный режим'?",
                             "answers": [
                                 {"reply": "Правила поведения людей, порядок организации производства и содержания помещений", "correctly": true},
                                 {"reply": "Регулярное проведение учений", "correctly": false},
                                 {"reply": "Только наличие плана эвакуации", "correctly": false}
                             ],
                             "marks": 4,
                             "time": 90
                         },
                         {
                             "content": "Какой цвет имеет знак пожарной опасности?",
                             "answers": [
                                 {"reply": "Красный", "correctly": true},
                                 {"reply": "Желтый", "correctly": false},
                                 {"reply": "Зеленый", "correctly": false}
                             ],
                             "marks": 2,
                             "time": 45
                         },
                         {
                             "content": "Что такое автономный дымовой извещатель?",
                             "answers": [
                                 {"reply": "Прибор, обнаруживающий дым и автоматически включающий звуковую сигнализацию", "correctly": true},
                                 {"reply": "Устройство для автоматического открытия окон", "correctly": false},
                                 {"reply": "Система контроля температуры помещения", "correctly": false}
                             ],
                             "marks": 3,
                             "time": 60
                         },
                         {
                             "content": "Какие меры предосторожности следует соблюдать при работе с легковоспламеняющимися жидкостями?",
                             "answers": [
                                 {"reply": "Хранить вдали от источников тепла и открытого огня", "correctly": true},
                                 {"reply": "Использовать рядом с источниками питания", "correctly": false},
                                 {"reply": "Держать емкости открытыми", "correctly": false}
                             ],
                             "marks": 3,
                             "time": 60
                         },
                         {
                             "content": "Что такое предел огнестойкости конструкции?",
                             "answers": [
                                 {"reply": "Время, в течение которого конструкция сохраняет несущую способность при воздействии огня", "correctly": true},
                                 {"reply": "Температура возгорания материала", "correctly": false},
                                 {"reply": "Количество используемого огнезащитного покрытия", "correctly": false}
                             ],
                             "marks": 4,
                             "time": 90
                         },
                         {
                             "content": "Как называется система, предназначенная для предотвращения распространения дыма и продуктов горения в зданиях?",
                             "answers": [
                                 {"reply": "Противодымная вентиляция", "correctly": true},
                                 {"reply": "Автоматическая пожарная сигнализация", "correctly": false},
                                 {"reply": "Гидрантная система", "correctly": false}
                             ],
                             "marks": 3,
                             "time": 60
                         },
                         {
                             "content": "Что необходимо сделать перед началом работ с использованием сварочного оборудования?",
                             "answers": [
                                 {"reply": "Очистить рабочее место от горючих материалов", "correctly": true},
                                 {"reply": "Оставить оборудование подключенным к сети", "correctly": false},
                                 {"reply": "Обеспечить доступ свежего воздуха", "correctly": false}
                             ],
                             "marks": 3,
                             "time": 60
                         },
                         {
                             "content": "Что относится к основным причинам возгораний на предприятиях?",
                             "answers": [
                                 {"reply": "Нарушение технологического процесса", "correctly": true},
                                 {"reply": "Использование качественного сырья", "correctly": false},
                                 {"reply": "Соблюдение норм охраны труда", "correctly": false}
                             ],
                             "marks": 3,
                             "time": 60
                         },
                         {
                             "content": "Какие действия являются правильными при обнаружении очага возгорания?",
                             "answers": [
                                 {"reply": "Сообщить о происшествии и приступить к ликвидации возгорания", "correctly": true},
                                 {"reply": "Самостоятельно ликвидировать очаг без привлечения помощи", "correctly": false},
                                 {"reply": "Игнорировать ситуацию и продолжить работу", "correctly": false}
                             ],
                             "marks": 3,
                             "time": 60
                         },
                         {
                             "content": "Какой вид огнетушителя используется для тушения горящих нефтепродуктов?",
                             "answers": [
                                 {"reply": "Порошковый огнетушитель", "correctly": true},
                                 {"reply": "Воздушно-пенный огнетушитель", "correctly": false},
                                 {"reply": "Водяной огнетушитель", "correctly": false}
                             ],
                             "marks": 3,
                             "time": 60
                         },
                         {
                             "content": "Какие вещества относятся к классу B по классификации пожаров?",
                             "answers": [
                                 {"reply": "Жидкости", "correctly": true},
                                 {"reply": "Газообразные вещества", "correctly": false},
                                 {"reply": "Электрическое оборудование", "correctly": false}
                             ],
                             "marks": 3,
                             "time": 60
                         },
                         {
                             "content": "Какова основная цель автоматической установки пожаротушения?",
                             "answers": [
                                 {"reply": "Предотвращение распространения пламени и минимизация ущерба", "correctly": true},
                                 {"reply": "Поддержание нормальной температуры в помещении", "correctly": false},
                                 {"reply": "Контроль доступа сотрудников", "correctly": false}
                             ],
                             "marks": 3,
                             "time": 60
                         },
                         {
                             "content": "Что представляет собой средство индивидуальной защиты органов дыхания?",
                             "answers": [
                                 {"reply": "Респиратор или противогаз", "correctly": true},
                                 {"reply": "Огнеупорный костюм", "correctly": false},
                                 {"reply": "Костюм химической защиты", "correctly": false}
                             ],
                             "marks": 3,
                             "time": 60
                         },
                         {
                             "content": "Какое основное назначение плана эвакуации?",
                             "answers": [
                                 {"reply": "Указание путей безопасного выхода из здания", "correctly": true},
                                 {"reply": "Определение мест хранения имущества", "correctly": false},
                                 {"reply": "Инструкция по использованию инструментов", "correctly": false}
                             ],
                             "marks": 3,
                             "time": 60
                         },
                         {
                             "content": "Что такое зона повышенной пожарной опасности?",
                             "answers": [
                                 {"reply": "Участок территории, где риск возникновения пожара значительно выше среднего", "correctly": true},
                                 {"reply": "Место временного нахождения пожарных расчетов", "correctly": false},
                                 {"reply": "Пространство возле гидрантов", "correctly": false}
                             ],
                             "marks": 3,
                             "time": 60
                         }
                     ]
                 }
                """, GeneratedQuiz.class);
    }
}
