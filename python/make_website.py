# Ziyu Zhao
# 71435169


def open_read_close_file(filename):
    """Reads the file as a list into the memory of the program"""
    # use with open() as function which reads the file and closes the file automatically
    with open(filename, 'r') as file:
        file = file.readlines()
    return file


def get_name(resume):
    """Returns a string as the name detected in resume
    and raises RuntimeError if the name is not capitalized"""
    # extract the first line as the name line
    name = resume[0].strip()
    # check if the person's name is properly capitalized
    for word in name.split(' '):
        if word != word.capitalize():
            # not capitalized raises RuntimeError
            raise RuntimeError('Name is not capitalized.')
    return name


def get_email(resume):
    """Returns a string as email detected by @ or .com/.edu without @"""
    email = ""
    for line in resume:
        # look for the line that has @
        if "@" in line:
            email_line = line.strip()
            # check if last four characters are .com or .edu
            if email_line[-4:] == ".com" or email_line[-4:] == ".edu":
                # extract the part between @ and the ending
                case_check = email_line.split("@")
                case_check1 = case_check[1].split(".")
                # check if the extracted part begins with a normal lowercase
                if case_check1[0] == case_check1[0].lower():
                    for char in email_line:
                        # check if there are numbers in the email
                        if char.isdigit():
                            print("Numbers in email.")
                            # once runs into a digit, email is regraded missing
                            return email
                        else:
                            pass
                    # if the email meets all the criteria, update the line as email
                    email = email_line
                else:
                    # a uppercase letter follows @, email missing
                    print("Upper cases in email.")
            else:
                # not ended with .com or .edu, email is missing
                print("No .com/.edu ended email detected.")
        else:
            # if the email has not @ for some reason, detect it by .com/.edu
            if ".com" in line or ".edu" in line:
                email_line = line.strip()
                for char in email_line:
                    # check for numbers
                    if char.isdigit():
                        print("Numbers in email.")
                        return email
                    else:
                        pass
                email = email_line
    return email


def get_courses(resume):
    """Returns a string consists of courses and clean the string"""
    # look for word "Courses"
    course_line = ""
    for line in resume:
        if "Courses" in line:
            # extract the line and split "Courses" from the line
            course_line = line.strip().split("Courses").pop(1)
            course_line = list(course_line)
            # clean up special punctuations until reaches a letter of alphabet
            while not course_line[0].isalpha():
                course_line.pop(0)
            course_line = "".join(course_line).strip()
    return course_line


def get_project(resume):
    """Returns a list of projects"""
    # look for the word "Projects" in the file
    line_num = 0
    for line in resume:
        if "Projects" in line:
            # extract the index of the line of "Projects"
            line_num = resume.index(line)
    i = line_num + 1
    projects = []
    # read lines and extract lines after "Projects" until meet minus signs
    while "------" not in resume[i]:
        if resume[i] is not '\n':
            # append the lines read into a list
            projects.append(resume[i].strip())
        i += 1
    return projects


def surround_block(tag, text):
    """Surrounds the given text with the give HTML tag and returns the string"""
    # trun the string into an editable list first
    text = list(text)
    # add <tag> and </tag> in the beginning and the end of the text
    text.insert(0, ("<{}>".format(tag)))
    text.append("<{}>".format("/" + tag))
    # put the list back together as a string
    text = "".join(text)
    return text


def create_email_link(email_address):
    """Create an email link with the given email address"""
    # replace @ with [aT] if there is an @ in the email
    if "@" in email_address:
        email_ad = email_address.replace("@", "[aT]")
    else:
        # if there's no at, leave the eamil as it was extracted in get_email
        email_ad = email_address
    # formatted the email address into a link readable by html
    email_link = '<a href="mailto:{}">{}</a>'.format(email_address, email_ad)
    return email_link


def info_section_html(name, email):
    """Formats the name and email into the html information section"""
    # use \n particularly to meet the formatting requirement
    info = "<div>\n{}\n<p>Email: {}</p>\n</div>".format(
        surround_block("h1", name),
        create_email_link(email)
    )
    return info


def project_section_html(projects):
    """Writes the projects into html formats"""
    # because each project has its own line, {}.format doesn't work
    # so I used multiple layers of surround_block to achieve the effect
    # surrounded Projects with <h2>
    proj_headline = surround_block("h2", "Projects")
    proj_list = []
    # surround each project with <li>
    for project in projects:
        proj_output = surround_block("li", project)
        proj_list.append(proj_output)

    # surround projects with <ul>
    proj_ul = surround_block("ul", proj_list)
    # surround all above with <div>
    proj = surround_block("div", proj_headline + proj_ul)
    return proj


def course_section_html(courses):
    """Writes the courses into html formats"""
    cour = "<div>\n{}\n{}\n</div>".format(
        surround_block("h3", "Courses"),
        surround_block("span", courses)
    )
    return cour


def export_html(content, file_name):
    """Writes into the given file and exports the updated file as a new .html"""
    # decides file type by edit its name attributes
    file_name += ".html"
    # create a new file
    fout = open(file_name, 'w')
    # write into the new file
    fout.writelines(content)
    # close the file
    fout.close()


def main():

    """Formats the txt resume into an html file"""
    # open and read resume.txt
    resume_in = open_read_close_file("resume_brandon.txt")
    # open and read html template
    html = open_read_close_file("resume_template.html")

    # edit the html before inserting body content
    # record and remove the last two lines
    last_two_lines = [html[-2], html[-1]]
    html.pop()
    html.pop()
    # enclose the body with the starting tag
    html.append('<div id="page-wrap">')

    # insert contents of info, project and courses sections by calling former functions
    html.append(info_section_html(get_name(resume_in), get_email(resume_in)))
    html.append(project_section_html(get_project(resume_in)))
    html.append(course_section_html(get_courses(resume_in)))

    # enclose the body with the ending tag
    html.append('</div>')
    # add the lat two lines back to file
    html.extend(last_two_lines)

    # export the updated file as a new file resume.html
    export_html(html, "resume")


if __name__ == '__main__':
    main()
