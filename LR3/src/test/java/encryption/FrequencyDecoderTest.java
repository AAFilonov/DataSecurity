package encryption;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class FrequencyDecoderTest {
    private static СeasarEncoder encoder;
    private static FrequencyDecoder decoder;

    @BeforeAll
    public static void setUp() throws IOException {
        encoder = new СeasarEncoder(CryptoEncoder.baseAlphabetEN, 6, "schoenabatic");
        decoder = new FrequencyDecoder(CryptoEncoder.baseAlphabetEN, "src/main/resources/Hamlet.txt");
    }

    @Test
    public void testInit() throws IOException {
        var encoder = new СeasarEncoder(CryptoEncoder.baseAlphabetEN, 6, "Salad");
        var decoder = new FrequencyDecoder(CryptoEncoder.baseAlphabetEN, "src/main/resources/Hamlet.txt");
        decoder.baseAlphabet_frequencies.forEach(((character, aDouble) -> {
            System.out.printf("[%c,%f]\n", character, aDouble);
        }));
    }


    @Test
    public void testcountFrequenciess() {
        var data = "ab";
        var frequencyes = decoder.countFrequencies(data);
        frequencyes.forEach(((character, aDouble) -> {
            System.out.printf("[%c,%f]\n", character, aDouble);
        }
        ));
    }


    @Test
    public void testDoHack() {
        var data = "\n" +
                "Ber.\n" +
                "See, it stalks away!\n" +
                "\n" +
                "Hor.\n" +
                "Stay! speak, speak! I charge thee speak!\n" +
                "\n" +
                "[Exit Ghost.]\n" +
                "\n" +
                "Mar.\n" +
                "'Tis gone, and will not answer.\n" +
                "\n" +
                "Ber.\n" +
                "How now, Horatio! You tremble and look pale:\n" +
                "Is not this something more than fantasy?\n" +
                "What think you on't?\n" +
                "\n" +
                "Hor.\n" +
                "Before my God, I might not this believe\n" +
                "Without the sensible and true avouch\n" +
                "Of mine own eyes.\n" +
                "\n" +
                "Mar.\n" +
                "Is it not like the King?\n" +
                "\n" +
                "Hor.\n" +
                "As thou art to thyself:\n" +
                "Such was the very armour he had on\n" +
                "When he the ambitious Norway combated;\n" +
                "So frown'd he once when, in an angry parle,\n" +
                "He smote the sledded Polacks on the ice.\n" +
                "'Tis strange.\n" +
                "\n" +
                "Mar.\n" +
                "Thus twice before, and jump at this dead hour,\n" +
                "With martial stalk hath he gone by our watch.\n" +
                "\n" +
                "Hor.\n" +
                "In what particular thought to work I know not;\n" +
                "But, in the gross and scope of my opinion,\n" +
                "This bodes some strange eruption to our state.\n" +
                "\n" +
                "Mar.\n" +
                "Good now, sit down, and tell me, he that knows,\n" +
                "Why this same strict and most observant watch\n" +
                "So nightly toils the subject of the land;\n" +
                "And why such daily cast of brazen cannon,\n" +
                "And foreign mart for implements of war;\n" +
                "Why such impress of shipwrights, whose sore task\n" +
                "Does not divide the Sunday from the week;\n" +
                "What might be toward, that this sweaty haste\n" +
                "Doth make the night joint-labourer with the day:\n" +
                "Who is't that can inform me?\n" +
                "\n" +
                "Hor.\n" +
                "That can I;\n" +
                "At least, the whisper goes so. Our last king,\n" +
                "Whose image even but now appear'd to us,\n" +
                "Was, as you know, by Fortinbras of Norway,\n" +
                "Thereto prick'd on by a most emulate pride,\n" +
                "Dar'd to the combat; in which our valiant Hamlet, — \n" +
                "For so this side of our known world esteem'd him, — \n" +
                "Did slay this Fortinbras; who, by a seal'd compact,\n" +
                "Well ratified by law and heraldry,\n" +
                "Did forfeit, with his life, all those his lands,\n" +
                "Which he stood seiz'd of, to the conqueror:\n" +
                "Against the which, a moiety competent\n" +
                "Was gaged by our king; which had return'd\n" +
                "To the inheritance of Fortinbras,\n" +
                "Had he been vanquisher; as by the same cov'nant,\n" +
                "And carriage of the article design'd,\n" +
                "His fell to Hamlet. Now, sir, young Fortinbras,\n" +
                "Of unimproved mettle hot and full,\n" +
                "Hath in the skirts of Norway, here and there,\n" +
                "Shark'd up a list of lawless resolutes,\n" +
                "For food and diet, to some enterprise\n" +
                "That hath a stomach in't; which is no other, — \n" +
                "As it doth well appear unto our state, — \n" +
                "But to recover of us, by strong hand,\n" +
                "And terms compulsatory, those foresaid lands\n" +
                "So by his father lost: and this, I take it,\n" +
                "Is the main motive of our preparations,\n" +
                "The source of this our watch, and the chief head\n" +
                "Of this post-haste and romage in the land.\n" +
                "\n" +
                "Ber.\n" +
                "I think it be no other but e'en so:\n" +
                "Well may it sort, that this portentous figure\n" +
                "Comes armed through our watch; so like the king\n" +
                "That was and is the question of these wars.\n" +
                "\n" +
                "Hor.\n" +
                "A mote it is to trouble the mind's eye.\n" +
                "In the most high and palmy state of Rome,\n" +
                "A little ere the mightiest Julius fell,\n" +
                "The graves stood tenantless, and the sheeted dead\n" +
                "Did squeak and gibber in the Roman streets;\n" +
                "As, stars with trains of fire and dews of blood,\n" +
                "Disasters in the sun; and the moist star,\n" +
                "Upon whose influence Neptune's empire stands,\n" +
                "Was sick almost to doomsday with eclipse:\n" +
                "And even the like precurse of fierce events, — \n" +
                "As harbingers preceding still the fates,\n" +
                "And prologue to the omen coming on, — \n" +
                "Have heaven and earth together demonstrated\n" +
                "Unto our climature and countrymen. — \n" +
                "But, soft, behold! lo, where it comes again!\n" +
                "\n" +
                "[Re-enter Ghost.]\n" +
                "\n" +
                "I'll cross it, though it blast me. — Stay, illusion!\n" +
                "If thou hast any sound, or use of voice,\n" +
                "Speak to me:\n" +
                "If there be any good thing to be done,\n" +
                "That may to thee do ease, and, race to me,\n" +
                "Speak to me:\n" +
                "If thou art privy to thy country's fate,\n" +
                "Which, happily, foreknowing may avoid,\n" +
                "O, speak!\n" +
                "Or if thou hast uphoarded in thy life\n" +
                "Extorted treasure in the womb of earth,\n" +
                "For which, they say, you spirits oft walk in death,\n" +
                "[The cock crows.]\n" +
                "Speak of it: — stay, and speak! — Stop it, Marcellus!\n" +
                "\n" +
                "Mar.\n" +
                "Shall I strike at it with my partisan?\n" +
                "\n" +
                "Hor.\n" +
                "Do, if it will not stand.\n" +
                "\n" +
                "Ber.\n" +
                "'Tis here!\n" +
                "\n" +
                "Hor.\n" +
                "'Tis here!\n" +
                "\n" +
                "Mar.\n" +
                "'Tis gone!\n" +
                "\n" +
                "[Exit Ghost.]\n" +
                "\n" +
                "We do it wrong, being so majestical,\n" +
                "To offer it the show of violence;\n" +
                "For it is, as the air, invulnerable,\n" +
                "And our vain blows malicious mockery.\n" +
                "\n" +
                "Ber.\n" +
                "It was about to speak, when the cock crew.\n" +
                "\n" +
                "Hor.\n" +
                "And then it started, like a guilty thing\n" +
                "Upon a fearful summons. I have heard\n" +
                "The cock, that is the trumpet to the morn,\n" +
                "Doth with his lofty and shrill-sounding throat\n" +
                "Awake the god of day; and at his warning,\n" +
                "Whether in sea or fire, in earth or air,\n" +
                "The extravagant and erring spirit hies\n" +
                "To his confine: and of the truth herein\n" +
                "This present object made probation.\n" +
                "\n" +
                "Mar.\n" +
                "It faded on the crowing of the cock.\n" +
                "Some say that ever 'gainst that season comes\n" +
                "Wherein our Saviour's birth is celebrated,\n" +
                "The bird of dawning singeth all night long;\n" +
                "And then, they say, no spirit dare stir abroad;\n" +
                "The nights are wholesome; then no planets strike,\n" +
                "No fairy takes, nor witch hath power to charm;\n" +
                "So hallow'd and so gracious is the time.\n" +
                "\n" +
                "Hor.\n" +
                "So have I heard, and do in part believe it.\n" +
                "But, look, the morn, in russet mantle clad,\n" +
                "Walks o'er the dew of yon high eastward hill:\n" +
                "Break we our watch up: and by my advice,\n" +
                "Let us impart what we have seen to-night\n" +
                "Unto young Hamlet; for, upon my life,\n" +
                "This spirit, dumb to us, will speak to him:\n" +
                "Do you consent we shall acquaint him with it,\n" +
                "As needful in our loves, fitting our duty?\n" +
                "\n" +
                "Mar.\n" +
                "Let's do't, I pray; and I this morning know\n" +
                "Where we shall find him most conveniently.\n" +
                "\n" +
                "[Exeunt.]\n" +
                "\n" +
                "Scene 2\n" +
                "\n" +
                "Elsinore. A room of state in the Castle.\n" +
                "\n" +
                "[Enter the King, Queen, Hamlet, Polonius, Laertes, Voltimand,\n" +
                "Cornelius, Lords, and Attendant.]\n" +
                "\n" +
                "King.\n" +
                "Though yet of Hamlet our dear brother's death\n" +
                "The memory be green, and that it us befitted\n" +
                "To bear our hearts in grief, and our whole kingdom\n" +
                "To be contracted in one brow of woe;\n" +
                "Yet so far hath discretion fought with nature\n" +
                "That we with wisest sorrow think on him,\n" +
                "Together with remembrance of ourselves.\n" +
                "Therefore our sometime sister, now our queen,\n" +
                "Th' imperial jointress to this warlike state,\n" +
                "Have we, as 'twere with a defeated joy, — \n" +
                "With an auspicious and one dropping eye,\n" +
                "With mirth in funeral, and with dirge in marriage,\n" +
                "In equal scale weighing delight and dole, — \n" +
                "Taken to wife; nor have we herein barr'd\n" +
                "Your better wisdoms, which have freely gone\n" +
                "With this affair along: — or all, our thanks.\n" +
                "Now follows, that you know, young Fortinbras,\n" +
                "Holding a weak supposal of our worth,\n" +
                "Or thinking by our late dear brother's death\n" +
                "Our state to be disjoint and out of frame,\n" +
                "Colleagued with this dream of his advantage,\n" +
                "He hath not fail'd to pester us with message,\n" +
                "Importing the surrender of those lands\n" +
                "Lost by his father, with all bonds of law,\n" +
                "To our most valiant brother. So much for him, — \n" +
                "Now for ourself and for this time of meeting:\n" +
                "Thus much the business is: — we have here writ\n" +
                "To Norway, uncle of young Fortinbras, — \n" +
                "Who, impotent and bed-rid, scarcely hears\n" +
                "Of this his nephew's purpose, — to suppress\n" +
                "His further gait herein; in that the levies,\n" +
                "The lists, and full proportions are all made\n" +
                "Out of his subject: — and we here dispatch\n";

        var encrypted_data = encoder.encrypt(data);

        var keyword = decoder.doHack(encrypted_data);
        var encoderWithKeywordFound = new СeasarEncoder(CryptoEncoder.baseAlphabetEN, 6, keyword);
        System.out.printf("%s", encoderWithKeywordFound.decrypt(encrypted_data));
    }

}