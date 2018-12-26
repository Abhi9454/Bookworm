package com.bookworm.bookworm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class subjectview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjectview);
        Intent subintent = getIntent();
        final String semester = subintent.getStringExtra("semester");
        final String course = subintent.getStringExtra("course");
        ListView sublist = (ListView) findViewById(R.id.subjectlist);
        ImageView imageView = (ImageView) findViewById(R.id.imagessubject);
        final Bundle extra=new Bundle();

        //bcom list
        final String[] bcom1 = new String[]{"financial accounting", "mercantile laws", "workshop on computer applications", "micro economics", "funtional communication skills-1", "principles of management and organization behavior", "workshop on excel modelling"};
        final String[] bcom2 = new String[]{"financial accounting-2", "workshopon computerized accounting", "company law", "environment and ecology", "micro economics", "advanced workshopon excel modelling", "communication skills-2"};
        final String[] bcom3 = new String[]{"auditing theory and practice", "corporate accounting", "meetings incentives conventions and exhibitions", "business ethics and values", "essentials of marketing", "analytical skills-1", "quantitative techniques"};
        final String[] bcom4 = new String[]{"corporate accounting 2", "cost accounting", "e-commerce", "research methodology", "enterpreneurship development", "workshop on business plan", "analytical skills-2"};
        final String[] bcom5 = new String[]{"managment accounting", "income tax laws", "workshopon practical taxation", "indian financial system", "banking insurance", "general bank operations", "business tourism and event management"};
        final String[] bcom6 = new String[]{"indirect tax laws", "legal aspects in banking and insurance", "indian economics problemsand policies", "basic financial management", "travel agency and tour operartion", "corporate strategy", "marketing of financial servics", "communication skills-3"};

        //cse list
        final String[] cse1 = new String[]{"Maths", "Physics", "Basic electrical and electronics engineering", "Mechanics", "Computer programming", "Environmental studies"};
        final String[] cse2 = new String[]{"Biology", "Enginering chemistry", "OOPs", "Engineering Graphics", "Differential equations", "Applied physics"};
        final String[] cse3 = new String[]{"Data structure and algorithm", "Computer organisation and design", "Digital electronics", "Python programming", "Database", "Discrete mathematics"};
        final String[] cse4 = new String[]{"Computer networks", "Programming in java", "Operatin Systems", "Software engineering", "Automata", "Probability and statics", "Design and analysis of algorithm"};
        final String[] cse5 = new String[]{"Computer graphics and visualization", "Compiler design", "Cyber security", "SAP", "Quantative aptitude"};
        final String[] cse6 = new String[]{"Cloud computing", "Information system security", "Securing computing systems", "Artificial intelligence", "Reasoning aptitude"};
        final String[] cse7 = new String[]{"Information security", "Penetration testing", "Mobile computing"};
        final String[] cse8 = new String[]{"Cryptography", "Data storage", "Software management", "Open source"};

        //bsc list
        final String[] bsc1 = new String[]{"mercantile laws", "workshop on computer applications", "environmental studies", "micro economics", "principles and practices of management", "entrepreneurship development", "communication skills-1"};
        final String[] bsc2 = new String[]{"financial accounting", "macro economics", "essentials of organization behaviour", "workshop on excel modelling", "business ethics", "business communication skills-1"};
        final String[] bsc3 = new String[]{"cost and management accounting", "banking and insurance", "essentials of marketing", "introduction to acting", "quantitative aptitude", "business communication skills-2", "business mathematics"};
        final String[] bsc4 = new String[]{"company law", "basic financial management", "human resource management", "building a character", "reasoning aptitude", "soft skills", "quantitative techniques"};
        final String[] bsc5 = new String[]{"research methodology", "business environment", "summer project", "event management", "essentials of consumer behavior", "production and perations management", "creating a role"};
        final String[] bsc6 = new String[]{"introduction to international business", "corporate strategy", "e-business", "fundamentals of promotions managament", "selling and sales management", "scene work"};

        //mech list
        final String[] mech1 = new String[]{"Maths", "Physics", "Basic electrical and electronics engineering", "Mechanics", "Computer programming", "Environmental studies"};
        final String[] mech2 = new String[]{"Biology", "Enginering chemistry", "OOPs", "Engineering Graphics", "Differential equations", "Applied physics"};
        final String[] mech3 = new String[]{"Kinematics", "Thermodynamics", "Material technology", "Electrical technology", "Industrial drafting and machine design"};
        final String[] mech4 = new String[]{"Dynamicsd of machinery", "Mathematics for engineering", "Manufacturing process", "Fluid mechanics"};
        final String[] mech5 = new String[]{"Dynamics of machinery 2", "Power plant engineering", "Fluid power engineering", "Heat and mass transfer", "Controlengineering"};
        final String[] mech6 = new String[]{"Controlengineering", "Machine design 1", "Thermal engineering", "Law for enguineers"};
        final String[] mech7 = new String[]{"Computer aided design", "Machine design 2", "Computer aided manufacturing"};
        final String[] mech8 = new String[]{"Major Projects"};

        //civil list
        final String[] cvl1 = new String[]{"Maths", "Physics", "Basic electrical and electronics engineering", "Mechanics", "Computer programming", "Environmental studies"};
        final String[] cvl2 = new String[]{"Biology", "Enginering chemistry", "OOPs", "Engineering Graphics", "Differential equations", "Applied physics"};
        final String[] cvl3 = new String[]{"Building and town planning", "Concrete technology", "Surveying 1", "Structural mechanics", "Ethics and values"};
        final String[] cvl4 = new String[]{"Geology and geotechnical engineering", "Structural mechanics 2", "Building construction", "Surveying 2", "Fluid mechanics"};
        final String[] cvl5 = new String[]{"Design of structure 1", "Water resources and irrrigation", "Transportation engineering  1", "Advanced construction", "Foundation engineering"};
        final String[] cvl6 = new String[]{"Design of structure 2", "Environmental engineering", "Transpotation engineering 2", "Law for engineer"};
        final String[] cvl7 = new String[]{"Professional pratices", "Design of structures 3", "Construction and project management"};
        final String[] cvl8 = new String[]{"Major Projects"};

        //ece list
        final String[] ece1 = new String[]{"Maths", "Physics", "Basic electrical and electronics engineering", "Mechanics", "Computer programming", "Environmental studies"};
        final String[] ece2 = new String[]{"Biology", "Enginering chemistry", "OOPs", "Engineering Graphics", "Differential equations", "Applied physics"};
        final String[] ece3 = new String[]{"Electronic devices and circuits 1", "Digital electronics", "Network analysics", "Linear control system", "Vector calculus", "Economics for engineeering"};
        final String[] ece4 = new String[]{"Signal and systems", "Electronics devices and circuits 2", "Electrical machines and drives", "Communication systems", "Electronics design"};
        final String[] ece5 = new String[]{"Electromagnetics engineering", "Integrated circuits and applications", "Microprocessor and computer architecture", "Modern measurement and instrumentation", "Digital electronics", "Microprocessor and microcontroller"};
        final String[] ece6 = new String[]{"Digital system design", "Antenna and wave propagation", "Fibre opticss", "Digital integrated circuit design", "Telecom  networks", "Modern processor archetecture"};
        final String[] ece7 = new String[]{"Satellite communiocation", "Data communicaion", "Embedded systems", "Microwave engineering", "Multimedia systems", "Wireless sensor", "VLSI design", "RF communication circuits"};
        final String[] ece8 = new String[]{"Major Projects"};

        final ArrayList<String> bcomlist1 = new ArrayList<String>();
        final ArrayList<String> cselist1 = new ArrayList<String>();
        final ArrayList<String> mechlist1 = new ArrayList<String>();
        final ArrayList<String> civillist1 = new ArrayList<String>();
        final ArrayList<String> eeelist1 = new ArrayList<String>();
        final ArrayList<String> bsclist1 = new ArrayList<String>();
        if (course.equals("Bachelor of Commerce")) {
            if (semester.equals("Semester 1")) {
                for (int i = 0; i < bcom1.length; ++i) {
                    bcomlist1.add(bcom1[i]);
                }
            }
            if (semester.equals("Semester 2")) {
                for (int i = 0; i < bcom2.length; ++i) {
                    bcomlist1.add(bcom2[i]);
                }
            }
            if (semester.equals("Semester 3")) {
                for (int i = 0; i < bcom3.length; ++i) {
                    bcomlist1.add(bcom3[i]);
                }
            }
            if (semester.equals("Semester 4")) {
                for (int i = 0; i < bcom4.length; ++i) {
                    bcomlist1.add(bcom4[i]);
                }
            }
            if (semester.equals("Semester 5")) {
                for (int i = 0; i < bcom5.length; ++i) {
                    bcomlist1.add(bcom5[i]);
                }
            }
            if (semester.equals("Semester 6")) {
                for (int i = 0; i < bcom6.length; ++i) {
                    bcomlist1.add(bcom6[i]);
                }
            }
        }


        //////////////////////////////////////////////////////////////////////////
        //////////////////cse/////////////////////////////////////////////////////////
        if (course.equals("B TECH CSE")) {
            if (semester.equals("Semester 1")) {
                for (int i = 0; i < cse1.length; ++i) {
                    cselist1.add(cse1[i]);
                }
            }
            if (semester.equals("Semester 2")) {
                for (int i = 0; i < cse2.length; ++i) {
                    cselist1.add(cse2[i]);
                }
            }
            if (semester.equals("Semester 3")) {
                for (int i = 0; i < cse3.length; ++i) {
                    cselist1.add(cse3[i]);
                }
            }
            if (semester.equals("Semester 4")) {
                for (int i = 0; i < cse4.length; ++i) {
                    cselist1.add(cse4[i]);
                }
            }
            if (semester.equals("Semester 5")) {
                for (int i = 0; i < cse5.length; ++i) {
                    cselist1.add(cse5[i]);
                }
            }
            if (semester.equals("Semester 6")) {
                for (int i = 0; i < cse6.length; ++i) {
                    cselist1.add(cse6[i]);
                }
            }
            if (semester.equals("Semester 7")) {
                for (int i = 0; i < cse7.length; ++i) {
                    cselist1.add(cse7[i]);
                }
            }
            if (semester.equals("Semester 8")) {
                for (int i = 0; i < cse8.length; ++i) {
                    cselist1.add(cse8[i]);
                }
            }
        }


        ///////////////////////////////////////////////////////////////////////////////
        ////////////////////bsc///////////////////////////
        if (course.equals("Bachelor of Science")) {
            if (semester.equals("Semester 1")) {
                for (int i = 0; i < bsc1.length; ++i) {
                    bsclist1.add(bsc1[i]);
                }
            }
            if (semester.equals("Semester 2")) {
                for (int i = 0; i < bsc2.length; ++i) {
                    bsclist1.add(bsc2[i]);
                }
            }
            if (semester.equals("Semester 3")) {
                for (int i = 0; i < bsc3.length; ++i) {
                    bsclist1.add(bsc3[i]);
                }
            }
            if (semester.equals("Semester 4")) {
                for (int i = 0; i < bsc4.length; ++i) {
                    bsclist1.add(bsc4[i]);
                }
            }
            if (semester.equals("Semester 5")) {
                for (int i = 0; i < bsc5.length; ++i) {
                    bsclist1.add(bsc5[i]);
                }
            }
            if (semester.equals("Semester 6")) {
                for (int i = 0; i < bsc6.length; ++i) {
                    bsclist1.add(bsc6[i]);
                }
            }
        }

        ///////////////////////////////////////////////////////////////////////////
        ////////////mechanical
        if (course.equals("B TECH Mechanical")) {
            if (semester.equals("Semester 1")) {
                for (int i = 0; i < mech1.length; ++i) {
                    mechlist1.add(mech1[i]);
                }
            }
            if (semester.equals("Semester 2")) {
                for (int i = 0; i < mech2.length; ++i) {
                    mechlist1.add(mech2[i]);
                }
            }
            if (semester.equals("Semester 3")) {
                for (int i = 0; i < mech3.length; ++i) {
                    mechlist1.add(mech3[i]);
                }
            }
            if (semester.equals("Semester 4")) {
                for (int i = 0; i < mech4.length; ++i) {
                    mechlist1.add(mech4[i]);
                }
            }
            if (semester.equals("Semester 5")) {
                for (int i = 0; i < mech5.length; ++i) {
                    mechlist1.add(mech5[i]);
                }
            }
            if (semester.equals("Semester 6")) {
                for (int i = 0; i < mech6.length; ++i) {
                    mechlist1.add(mech6[i]);
                }
            }
            if (semester.equals("Semester 7")) {
                for (int i = 0; i < mech7.length; ++i) {
                    mechlist1.add(mech7[i]);
                }
            }
            if (semester.equals("Semester 8")) {
                for (int i = 0; i < mech8.length; ++i) {
                    mechlist1.add(mech8[i]);
                }
            }
        }

        //////////////////////////////////////////////////////////////////////////////////
        ////////////civil
        if (course.equals("B TECH Civil")) {
            if (semester.equals("Semester 1")) {
                for (int i = 0; i < cvl1.length; ++i) {
                    civillist1.add(cvl1[i]);
                }
            }
            if (semester.equals("Semester 2")) {
                for (int i = 0; i < cvl2.length; ++i) {
                    civillist1.add(cvl2[i]);
                }
            }
            if (semester.equals("Semester 3")) {
                for (int i = 0; i < cvl3.length; ++i) {
                    civillist1.add(cvl3[i]);
                }
            }
            if (semester.equals("Semester 4")) {
                for (int i = 0; i < cvl4.length; ++i) {
                    civillist1.add(cvl4[i]);
                }
            }
            if (semester.equals("Semester 5")) {
                for (int i = 0; i < cvl5.length; ++i) {
                    civillist1.add(cvl5[i]);
                }
            }
            if (semester.equals("Semester 6")) {
                for (int i = 0; i < cvl6.length; ++i) {
                   civillist1.add(cvl6[i]);
                }
            }
            if (semester.equals("Semester 7")) {
                for (int i = 0; i < cvl7.length; ++i) {
                    civillist1.add(cvl7[i]);
                }
            }
            if (semester.equals("Semester 8")) {
                for (int i = 0; i < cvl8.length; ++i) {
                    civillist1.add(cvl8[i]);
                }
            }
        }

        ////////////////////////////////////////////////////////////////////////////////
        ///////////////////electronics

        if (course.equals("B TECH Electronics")) {
            if (semester.equals("Semester 1")) {
                for (int i = 0; i < ece1.length; ++i) {
                    eeelist1.add(ece1[i]);
                }
            }
            if (semester.equals("Semester 2")) {
                for (int i = 0; i < ece2.length; ++i) {
                    eeelist1.add(ece2[i]);
                }
            }
            if (semester.equals("Semester 3")) {
                for (int i = 0; i < ece3.length; ++i) {
                    eeelist1.add(ece3[i]);
                }
            }
            if (semester.equals("Semester 4")) {
                for (int i = 0; i < ece4.length; ++i) {
                    eeelist1.add(ece4[i]);
                }
            }
            if (semester.equals("Semester 5")) {
                for (int i = 0; i < ece5.length; ++i) {
                    eeelist1.add(ece5[i]);
                }
            }
            if (semester.equals("Semester 6")) {
                for (int i = 0; i < ece6.length; ++i) {
                    eeelist1.add(ece6[i]);
                }
            }
            if (semester.equals("Semester 7")) {
                for (int i = 0; i < ece7.length; ++i) {
                    eeelist1.add(ece7[i]);
                }
            }
            if (semester.equals("Semester 8")) {
                for (int i = 0; i < ece8.length; ++i) {
                    eeelist1.add(ece8[i]);
                }
            }

        }

        //////////////////////////////////////////////////////////////////////////////////
        ///////////arrayadapter
        ArrayAdapter<String> arraybcom = new ArrayAdapter<String>(getApplicationContext(), R.layout.semesterlist, R.id.semstertext, bcomlist1);
        ArrayAdapter<String> arraycse = new ArrayAdapter<String>(getApplicationContext(), R.layout.semesterlist, R.id.semstertext, cselist1);
        ArrayAdapter<String> arraymech = new ArrayAdapter<String>(getApplicationContext(), R.layout.semesterlist, R.id.semstertext, mechlist1);
        ArrayAdapter<String> arrayeee = new ArrayAdapter<String>(getApplicationContext(), R.layout.semesterlist, R.id.semstertext, eeelist1);
        ArrayAdapter<String> arraycivil = new ArrayAdapter<String>(getApplicationContext(), R.layout.semesterlist, R.id.semstertext, civillist1);
        ArrayAdapter<String> arraybsc = new ArrayAdapter<String>(getApplicationContext(), R.layout.semesterlist, R.id.semstertext, bsclist1);
        if (course.equals("Bachelor of Commerce")) {
            imageView.setImageResource(R.drawable.commerce);
            sublist.setAdapter(arraybcom);
        }
        if (course.equals("Bachelor of Science")) {
            imageView.setImageResource(R.drawable.science);
            sublist.setAdapter(arraybsc);
        }
        if (course.equals("B TECH CSE")) {
            imageView.setImageResource(R.drawable.computer);
            sublist.setAdapter(arraycse);
        }
        if (course.equals("B TECH Mechanical")) {
            imageView.setImageResource(R.drawable.machine);
            sublist.setAdapter(arraymech);
        }
        if (course.equals("B TECH Civil")) {
            imageView.setImageResource(R.drawable.civic);
            sublist.setAdapter(arraycivil);
        }
        if (course.equals("B TECH Electronics")) {
            imageView.setImageResource(R.drawable.elec);
            sublist.setAdapter(arrayeee);
        }


        sublist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                extra.putString("course",course);
                extra.putString("semester",semester);
                if(course.equals("B TECH CSE"))
                {
                    extra.putString("subject",cselist1.get(position));
                }
                if(course.equals("B TECH Mechanical"))
                {
                    extra.putString("subject",mechlist1.get(position));
                }
                if(course.equals("B TECH Electronics"))
                {
                    extra.putString("subject",eeelist1.get(position));
                }
                if(course.equals("B TECH Civil"))
                {
                    extra.putString("subject",civillist1.get(position));
                }
                if(course.equals("Bachelor of Science"))
                {
                    extra.putString("subject",bsclist1.get(position));
                }
                if(course.equals("Bachelor of Commerce"))
                {
                    extra.putString("subject",bcomlist1.get(position));
                }
                startActivity(new Intent(getApplicationContext(),browseitems.class).putExtras(extra).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
    }
}
