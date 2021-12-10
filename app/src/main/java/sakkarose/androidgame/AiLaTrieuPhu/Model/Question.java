package sakkarose.androidgame.AiLaTrieuPhu.Model;

public class Question
{
        public String Question;
        public String CaseA;
        public String CaseB;
        public String CaseC;
        public String CaseD;
        public int TrueCase;
        public int ID;

        public Question()
        {
        }

        public Question(String Question, String CaseA, String CaseB, String CaseC, String CaseD, int TrueCase, int ID)
        {
            this.Question = Question;
            this.CaseA = CaseA;
            this.CaseB = CaseB;
            this.CaseC = CaseC;
            this.CaseD = CaseD;
            this.TrueCase = TrueCase;
            this.ID = ID;
        }
}
