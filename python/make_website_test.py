import unittest
from make_website import *

class MyTestCase(unittest.TestCase):

    def setUp(self):
        self.resume = open_read_close_file("resume.txt")

    def test_open_read_close_file(self):
        # test if the file loaded successful by checking the first line
        self.assertEqual("Zoe Incredible", self.resume[0].strip())
        # test if the file loaded successfully by checking a part of the content
        self.assertIn("ggplot wants you dead",self.resume[1])
        # test if loading a new file works
        self.assertIn("zoe Incredible",open_read_close_file("resume name test lower case.txt")[0])

    def test_get_name(self):
        # check if it raises RuntimeError, when the name is not properly capitalized
        self.assertRaises(RuntimeError, get_name, open_read_close_file("resume name test lower case.txt"))
        self.assertRaises(RuntimeError, get_name, open_read_close_file("resume name test lower case 1.txt"))
        # check in a new file
        self.assertEqual("Doglas Cat", get_name(open_read_close_file("resume Dog.txt")))

    def test_get_email(self):
        # check if it returns "" when there are numbers in the email
        self.assertEqual("", get_email(open_read_close_file("resume email test 1 numbers.txt")))
        # check if it returns "" when the email end differently from .com or .edu
        self.assertEqual("", get_email(open_read_close_file("resume email test 2 ending org.txt")))
        # check if it returns "" when an upper case follows @
        self.assertEqual("", get_email(open_read_close_file("resume email test 3 upper case.txt")))
        # check if it returns "" when there is no @ in the email
        self.assertEqual("zincredible.seas.upenn.edu",
                         get_email(open_read_close_file("resume email test 4 no aT.txt")))
        # check if it returns "" when there's no @ but numbers in the email
        self.assertEqual("", get_email(open_read_close_file("resume email test 5 no aT with numbers.txt")))

    def test_get_courses(self):
        # check if key words in courses are included
        self.assertTrue("Java" in get_courses(self.resume))
        self.assertTrue("R: ggplot wants you dead" in get_courses(self.resume))
        # check if it returns False if the course is not in the courses line
        self.assertFalse("C++" in get_courses(self.resume))
        # check if it ignores special punctuations such as % after "Courses" before letters
        self.assertFalse("%" in get_courses(open_read_close_file("resume Dog.txt")))

    def test_get_project(self):
        # check if key words are in the result
        self.assertTrue("Midterm Mental Breakdown" in get_project(self.resume)[0])
        # check if the function ignores blank lines
        self.assertFalse("\n" in get_project(open_read_close_file("resume project test 1 blank lines&whitespace.txt")))
        # check if the function takes care of whitespaces
        self.assertFalse(" " == get_project(open_read_close_file("resume project test 1 blank lines&whitespace.txt"))[2][0])

    def test_surround_block(self):
        # check if the tag function works ok
        self.assertEqual('<h1>The Beatles</h1>', surround_block('h1','The Beatles'))
        self.assertEqual('<li>built a robot</li>', surround_block('li', 'built a robot'))

    def test_create_email_link(self):
        # check if the result of the function is as expected
        self.assertEqual('<a href="mailto:tom@seas.upenn.edu">tom[aT]seas.upenn.edu</a>',
                         create_email_link('tom@seas.upenn.edu'))
        self.assertEqual('<a href="mailto:lbrandon@wharton.upenn.edu">lbrandon[aT]wharton.upenn.edu</a>',
                         create_email_link('lbrandon@wharton.upenn.edu'))
        # check if the function deals with email without @ as required
        self.assertEqual('<a href="mailto:zincredible.seas.upenn.edu">zincredible.seas.upenn.edu</a>',
                         create_email_link(get_email(open_read_close_file("resume email test 4 no aT.txt"))))

    def test_info_section_html(self):
        # text if the info section formatting works as expected
        name = get_name(open_read_close_file("resume Dog.txt"))
        email = get_email(open_read_close_file("resume Dog.txt"))
        self.assertEqual('<div>\n<h1>Doglas Cat</h1>\n<p>Email: <a href="mailto:dogcat@zoo.edu">dogcat[aT]zoo.edu</a></p>\n</div>',
                         info_section_html(name,email))

    def test_project_section_html(self):
        # use a simple txt to test the project section html formatting
        self.assertEqual('<div><h2>Projects</h2><ul><li>Eat Happily.</li><li>Sleep tightly.</li><li>Dream wildly.</li></ul></div>',
                         project_section_html(get_project(open_read_close_file("resume Dog.txt"))))

    def test_course_section_html(self):
        # test if the course formatting is ok
        courses = get_courses(open_read_close_file("resume Dog.txt"))
        self.assertEqual("<div>\n<h3>Courses</h3>\n<span>Dog, Cat</span>\n</div>",course_section_html(courses))

if __name__ == '__main__':
    unittest.main()
